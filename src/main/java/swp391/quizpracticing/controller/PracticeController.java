package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IDimensionRepository;
import swp391.quizpracticing.repository.ISubcategoryRepository;
import swp391.quizpracticing.repository.ISubjectRepository;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.Quizreview;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.IQuestionService;
import swp391.quizpracticing.service.IQuizreviewQuestionService;
import swp391.quizpracticing.service.IQuizreviewService;

@Controller
@RequestMapping("/practice")
public class PracticeController {

    @Autowired
    private IQuizreviewService iQuizreviewService;

    @Autowired
    private ILessonService iLessonService;

    @Autowired
    private IQuizreviewQuestionService iQuizreviewQuestionService;

    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/detail")
    public String showFormPractice(@RequestParam(name = "id") Integer id, HttpSession session, HttpServletRequest request, Model model) {
        Quizreview quiztaked = iQuizreviewService.findById(id);
        LessonDTO lesson = iLessonService.findById(quiztaked.getLesson().getId());
        SubjectDTO subject = lesson.getSubject();
        Integer question = quiztaked.getLesson().getQuestions().size();
        model.addAttribute("takenQuizzes", quiztaked);
        model.addAttribute("takenQuizSubjects", subject);
        model.addAttribute("takenQuizLesson", lesson);
        model.addAttribute("numberquestion", question);
        model.addAttribute("id", id);

        model.addAttribute("userSession", session.getAttribute("user"));
        return "practice_detail/PracticeDetail";
    }
}
