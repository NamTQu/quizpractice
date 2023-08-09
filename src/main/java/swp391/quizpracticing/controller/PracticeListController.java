package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.dto.QuizreviewDTO;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.IQuestionService;
import swp391.quizpracticing.service.IQuizreviewQuestionService;
import swp391.quizpracticing.service.IQuizreviewService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import swp391.quizpracticing.dto.UserDTO;

@Controller
@RequestMapping("/user")
public class PracticeListController {

    @Autowired
    private IQuizreviewService iQuizreviewService;

    @Autowired
    private IQuizreviewQuestionService iQuizreviewQuestionService;


    @GetMapping("/practice-list")
    public String getToPracticeList(@RequestParam(name = "page", defaultValue = "0") Integer page, Model model, HttpSession session) {
        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        //Get all the Taken Quizzes of the respective user
        Page<Quizreview> takenQuizzes = iQuizreviewService.getAllQuizreviewsByUserId(loggedinUser.getId(), page);

        //Using HashMap to store quizreviewId and numberOfCorrect (quizreviewQuestion)
        HashMap<Integer, Integer> quizreviewQuestionHashMap = new HashMap<>();

        for(Quizreview takenQuiz : takenQuizzes) {
            Integer correct = iQuizreviewQuestionService.getNumberOfCorrectAnswerByQuizreviewId(takenQuiz.getId(), true);
            System.out.println("takenQuiz id: " + takenQuiz.getId() + ", correct: " + correct);
            quizreviewQuestionHashMap.put(takenQuiz.getId(), correct);
        }

        model.addAttribute("takenQuizzes", takenQuizzes);
        model.addAttribute("hashmaps", quizreviewQuestionHashMap);
        model.addAttribute("page", page);
        model.addAttribute("userSession", session.getAttribute("user"));

        return "practice_list/practice_list";
    }

    @GetMapping("/practice-list/search")
    public String searchByPracticeName(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "practice-name") String practiceName, Model model, HttpSession session) {

        System.out.println("Searching for practice list is called");

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        if(practiceName.isEmpty()) {
            model.addAttribute("examName", practiceName);
            model.addAttribute("userSession", session.getAttribute("user"));
            return "redirect:/user/practice-list";
        } else {

            Page<Quizreview> takenQuizzes = iQuizreviewService.findByLessonName(practiceName, loggedinUser.getId(), page);
            System.out.println("search result size: " + takenQuizzes.getTotalElements());

            //copy from /practice-list

            //Using HashMap to store quizreviewId and numberOfCorrect (quizreviewQuestion)
            HashMap<Integer, Integer> quizreviewQuestionHashMap = new HashMap<>();

            for(Quizreview takenQuiz : takenQuizzes) {
                Integer correct = iQuizreviewQuestionService.getNumberOfCorrectAnswerByQuizreviewId(takenQuiz.getId(), true);
                System.out.println("takenQuiz id: " + takenQuiz.getId() + ", correct: " + correct);
                quizreviewQuestionHashMap.put(takenQuiz.getId(), correct);
            }

            model.addAttribute("takenQuizzes", takenQuizzes);
            model.addAttribute("hashmaps", quizreviewQuestionHashMap);
            model.addAttribute("page", page);
            model.addAttribute("practice-name", practiceName);
            model.addAttribute("userSession", session.getAttribute("user"));

        }

        return "practice_list/practice_list";
    }
}
