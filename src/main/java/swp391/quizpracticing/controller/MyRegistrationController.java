package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.IPricepackageRepository;
import swp391.quizpracticing.repository.IRegistrationstatusRepository;
import swp391.quizpracticing.repository.IUserSubjectRepository;
import swp391.quizpracticing.service.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;

import swp391.quizpracticing.dto.PricepackageDTO;

@Controller
@RequiredArgsConstructor
public class MyRegistrationController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserSubjectService userCourseService;
    @Autowired
    private ISubjectService subService;
    @Autowired
    private IPricepackageService packageService;
    @Autowired
    private IPricepackageRepository pricepackageRepository;
    @Autowired
    private IUserSubjectRepository userSubjectRepository;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private HttpSession userSession;
    @Autowired
    private IRegistrationstatusRepository iRegistrationstatusRepository;

    private List<MyRegistration> reg(Integer id) {
        List<Subject> subjects = userCourseService.courseById(id);
        List<MyRegistration> regis = new ArrayList<>(Collections.nCopies(subjects.size(), null));
        List<Date> dateRegis = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> registationId = new ArrayList<>();
        List<Integer> price = new ArrayList<>();

        for (UserSubject uc : userCourseService.listAll()) {
            if (uc.getUser().getId().intValue() == id) {
                for (Subject sub : subjects) {
                    if (sub.getId().intValue() == uc.getSubject().getId().intValue()) {

                        Date date = new Date(uc.getRegistrationTime().getTime());
                        dateRegis.add(date);
                        status.add(uc.getRegistrationStatus().getName());
                        registationId.add(uc.getUser().getId() + "" + uc.getSubject().getId());
                        price.add(uc.getPricePackage().getId());

                    }
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < subjects.size(); i++) {
            MyRegistration r = new MyRegistration();
            r.setCid(subjects.get(i).getId());
            r.setSubName(subjects.get(i).getBriefInfo());
            r.setRegisId(registationId.get(i));
            r.setDateRegis(dateRegis.get(i));
            r.setStatus(status.get(i));
            r.setTotalcost(packageService.getById( price.get(i)).getSalePrice());
            r.setPackageName(packageService.getById( price.get(i)).getName());

            Date date = dateRegis.get(i);
            Date date1 = dateRegis.get(i);
            calendar.setTime(date);
            if (r.getPackageName().contains("3")) {
                calendar.add(Calendar.DATE, 90);
                date = new Date(calendar.getTimeInMillis());
            } else if (r.getPackageName().contains("6")) {
                calendar.add(Calendar.DATE, 180);
                date = new Date(calendar.getTimeInMillis());
            } else {
                date = null;
            }
            r.setValidTo(date);
            r.setValidfrom(date1);
            regis.set(i, r);
        }
        return regis;
    }

    @GetMapping("/user/myregistration")
    public String showRegistration(Model model) {
        try {

            Object sessionInfo = userSession.getAttribute("user");
            UserDTO u = (UserDTO) sessionInfo;
            Integer uid = u.getId();
            List<MyRegistration> regis = reg(uid);
            List<Subject> subjects = userCourseService.courseById(uid);
            List<Category> cat = categoryService.listAll();
            model.addAttribute("userSession", sessionInfo);
            model.addAttribute("sub", subjects);
            model.addAttribute("cat", cat);
            model.addAttribute("regis", regis);

            return "customer/my_registration";
        } catch (NumberFormatException e) {
            return "redirect:/home";
        }
    }

    @GetMapping("/user/myregistration/{cid}")
    public String regisCourse(@PathVariable("cid") Integer cid, Model model) {
        try {
            Object sessionInfo = userSession.getAttribute("user");
            UserDTO u = (UserDTO) sessionInfo;
            Integer uid = u.getId();
            List<MyRegistration> regis = reg(u.getId());
            MyRegistration re = new MyRegistration();
            List<Category> cat = categoryService.listAll();
            model.addAttribute("cat", cat);

            for (MyRegistration r : regis) {
                if (Objects.equals(r.getCid(), cid)) {
                    re = r;
                    break;
                }
            }
            model.addAttribute("userSession", userSession.getAttribute("user"));
            model.addAttribute("uid", uid);
            model.addAttribute("re", re);
            SubjectDTO su = subService.getDTOById(cid);
            List<PricepackageDTO> price = packageService.getBySubjectId(cid);
            model.addAttribute("subject", su);
            model.addAttribute("pack", price);
            return "customer/subject_info";
        } catch (NumberFormatException e) {
            return "redirect:/home";
        }


    }
    @GetMapping("/user/registration/search")
    public String submitSearchForm( @RequestParam("search") String search,Model model) {

        List<Subject> subjects = subService.searchByCourseName(search);
        List<Category> cat = categoryService.listAll();
        model.addAttribute("cat", cat);
        model.addAttribute("sub", subjects);
        Object sessionInfo = userSession.getAttribute("user");
        UserDTO u = (UserDTO) sessionInfo;
        Integer uid = u.getId();
        List<MyRegistration> regis = reg(uid);
        model.addAttribute("regis", regis);

        model.addAttribute("userSession", userSession.getAttribute("user"));
        return "customer/myregistrationsearch";
    }

    @GetMapping("/user/registration/save/{uid}/{cid}")
    public String saveRegistration(@PathVariable("uid") Integer uid, @PathVariable("cid") Integer cid,
                                   @RequestParam("button") Integer but,
                                   HttpSession session, Model model) {
        List<UserSubject> userSubjects=userSubjectRepository.findAll();
        List<UserSubject> userSubjects1=new ArrayList<>();
        for (UserSubject u: userSubjects) {
            if (Objects.equals(u.getUser().getId(), uid) && Objects.equals(u.getSubject().getId(), cid)){
                userSubjects1.add(u);
            }
        }
        UserSubject us ;
        if (userSubjects1.isEmpty()) {
            us = new UserSubject();
        }
        else {
            us=userSubjects1.get(0);
        }

        us.setSubject(subService.getById(cid));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        us.setRegistrationTime(timestamp);
        us.setPricePackage(pricepackageRepository.getById(but));
        us.setRegistrationStatus(iRegistrationstatusRepository.getById(3));
        us.setUser(userService.getByUserId(uid));
        userCourseService.save(us);
//       us.se
        return "redirect:/user/myregistration/{cid}";
    }

}
