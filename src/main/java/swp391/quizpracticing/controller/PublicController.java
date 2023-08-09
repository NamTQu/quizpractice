/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import swp391.quizpracticing.dto.*;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.service.*;

/**
 *
 * @author Mosena
 */
@Controller
public class PublicController {
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ISubjectService subjectService;
    
    @Autowired
    private ICategoryService categoryService;
    
    @Autowired
    private IPricepackageService pricePackageService;
    
    @Autowired
    private IUserSubjectService userSubjectService;

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private IBlogcategoryService iBlogCategoryService;

    @GetMapping("/user-created/verify")
    public String verify(@RequestParam("code") String code, Model model){
        UserDTO u=userService.findUserByToken(code);
        if(u!=null){
            userService.updateUserStatusAndToken(u.getId(), Boolean.TRUE);
            return "/common/verify_success";
        }
        model.addAttribute("msg", "Get lost?");
        return "/admin/error";
    }
    
    @GetMapping("/user-created/discard")
    public String discard(@RequestParam("code") String code, Model model){
        UserDTO u=userService.findUserByToken(code);
        if(u!=null){
            userService.remove(u);
            return "/admin/discardaccount";
        }
        model.addAttribute("msg", "Get lost?");
        return "/admin/error";
    }
    
    @GetMapping("/registration-created/discard")
    public String registrationDiscard(@RequestParam("code") String code, @RequestParam("registrationId") String id, Model model){
        UserDTO u=userService.findUserByToken(code);
        UserSubjectDTO registration=userSubjectService
                .getRegistration(Integer.parseInt(id));
        if(u!=null){
            userSubjectService.remove(registration);
            userService.remove(u);
            return "/admin/discardaccount";
        }
        model.addAttribute("msg", "Get lost?");
        return "/admin/error";
    }
    @GetMapping("/subjects/subjects-list")
    public String getSubjects(HttpSession session,
            Model model,
            @RequestParam(name = "pageNo", defaultValue = "1") String sPageNo,
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @RequestParam(name = "subCategory", required = false) 
                    String[] subCategoriesArray){
        int pageSize = 6;
        Integer pageNo;
        List<Integer> subCategories=null;
        try{
            pageNo=Integer.parseInt(sPageNo);
            if(subCategoriesArray!=null){
            subCategories= Arrays.stream(subCategoriesArray)
                    .map(Integer::parseInt)
                    .toList();
            }
        }
        catch(NumberFormatException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Not found");
            return "/admin/error";
        }
        Page<SubjectDTO> page=subjectService.findAll(pageNo, pageSize, search, 
                order, subCategories);
        Integer totalPages=page.getTotalPages();
        if(totalPages==0 || pageNo<1 || pageNo>totalPages){
            model.addAttribute("msg", "Not found");
            return "/admin/error";
        }
        List<SubjectDTO> subjectList=page
                .stream()
                .toList();
        for(SubjectDTO subject:subjectList){
            List<PricepackageDTO> pricePackages=pricePackageService
                    .getBySubjectId(subject.getId());
            if(pricePackages.size()>0) {
                Float listPrice = pricePackages.get(0).getListPrice();
                Float salePrice = pricePackages.get(0).getSalePrice();
                model.addAttribute("subjectListPrice_"+subject.getId(),
                        listPrice);
                model.addAttribute("subjectSalePrice_"+subject.getId(),
                        salePrice);
            }
        }
        List<CategoryDTO> categoryList=categoryService.findAll();
        model.addAttribute("userSession", 
                session.getAttribute("user"));
        model.addAttribute("subjects", subjectList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPages", totalPages);
        return "/public/subjects";
    }
    
    @GetMapping("/subjects/subject-detail")
    public String getSubjectDetail(HttpSession session, 
            Model model,
            @RequestParam(name = "subjectId") String sSubjectId){
        Integer subjectId;
        try{
            subjectId=Integer.parseInt(sSubjectId);
        }
        catch(NumberFormatException ex){
            model.addAttribute("userSession", 
                session.getAttribute("user"));
            model.addAttribute("msg", "Not found");
            return "/admin/error";
        }
        SubjectDTO subject=subjectService.getDTOById(subjectId);
        List<CategoryDTO> categoryList=categoryService.findAll();
        model.addAttribute("subject", subject);
        model.addAttribute("categories", categoryList);
        model.addAttribute("userSession",
                session.getAttribute("user"));
        return "/public/subject-detail";
    }
    
    @PostMapping("/subject-register")
    public String subjectRegister(HttpSession session, 
            Model model,
            @RequestParam(name = "subjectId") String sSubjectId){
        return "redirect:/subjects/subjects-list";
    }
    
    @GetMapping("/get-pricepackages")
    @ResponseBody
    public List<PricepackageDTO> getPricePackages(
            @RequestParam("subjectId") String sSubjectId){
        Integer subjectId;
        try{
            subjectId=Integer.parseInt(sSubjectId);
        }
        catch(NumberFormatException ex){
            return null;
        }
        return pricePackageService.getBySubjectId(subjectId);
    }

    @GetMapping("/blog/blog-detail")
    public String showBlogDetails(@RequestParam(name = "id", required = true) Integer id, HttpSession session, Model model) {
        //Get latest blogs
        List<BlogDTO> latestBlogs = iBlogService.getLatestBlog(2);
        //Get all categories
        List<BlogcategoryDTO> postCategories = iBlogCategoryService.getAllCategories();

        Blog blog = iBlogService.getBlogById(id);
        model.addAttribute("lastestBlogs", latestBlogs);
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("blog", blog);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "public/blog_detail";
    }
    @GetMapping("/registration")
    public String registerSubject(@RequestParam("cid") Integer courseId, Model model) {

        Subject su = subjectService.getById(courseId);
        List<PricepackageDTO> price = pricePackageService.getBySubjectId(courseId);

        model.addAttribute("cid", courseId);
        model.addAttribute("subject",su);
        model.addAttribute("pack", price);

        return "public/subject_register";
    }
}
