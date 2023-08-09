package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.repository.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private ISubcategoryRepository iSubcategoryRepository;

    @Autowired
    private IDimensionRepository iDimensionRepository;

    @GetMapping("/lessonList")
    public String getData(@RequestParam(name = "id") Integer id,
                          @RequestParam(name = "categoryID",required = false) Integer categoryID,
                          @RequestParam(name = "status",required = false) Integer status,
                          @RequestParam(name = "search",required = false) String searchName,
                          Model model, HttpSession session){
        model.addAttribute("userSession", session.getAttribute("user"));
        Optional<Subject> optionalSubject = iSubjectRepository.findById(id);
        if(optionalSubject.isPresent()){
            Subject subject = optionalSubject.get();
            List<Lesson> lessonList = subject.getLessons();
            if(categoryID != null && categoryID != -1){
                lessonList = lessonList.stream()
                        .filter(lesson -> lesson.getSubCategories().stream()
                                .map(Subcategory::getId)
                                .anyMatch(cid -> Objects.equals(cid, categoryID)))
                        .collect(Collectors.toList());
                model.addAttribute("category",categoryID);
            }
            if(status != null && status != 0){
                boolean check = status == 1;
                lessonList = lessonList.stream().filter(n -> n.getStatus().booleanValue() == check).collect(Collectors.toList());
                model.addAttribute("status",status);
            }
            if(searchName != null && !searchName.trim().isEmpty()){
                lessonList = lessonList.stream().filter(n -> n.getName().toLowerCase().contains(searchName.trim().toLowerCase())).collect(Collectors.toList());
                model.addAttribute("search",searchName);
            }
            model.addAttribute("subject",subject);
            model.addAttribute("list",lessonList);
        }
        List<Subcategory> listSubCate = iSubcategoryRepository.findAll();
        model.addAttribute("listCate",listSubCate);
        model.addAttribute("subject_id", id);
        return "lesson/subjectLesson";
    }

    @PostMapping("/choose")
    public void changeSearch(@RequestParam("id") Integer id, HttpServletResponse response) throws IOException {
        if(id == 1){
            List<Subcategory> list = iSubcategoryRepository.findAll();
            for(Subcategory i : list){
                response.getWriter().println("<option value=\"" + i.getId() + "\">" + i.getName() + "</option>");
            }
        }
        else {
            List<Dimension> list = iDimensionRepository.findAll();
            for(Dimension i : list){
                response.getWriter().println("<option value=\"" + i.getId() + "\">" + i.getName() + "</option>");
            }
        }
    }
}
