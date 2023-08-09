/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.AnswerDTO;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.dto.QuestionDTO;
import swp391.quizpracticing.dto.QuizreviewDTO;
import swp391.quizpracticing.dto.QuizreviewQuestionDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.IQuizreviewQuestionService;
import swp391.quizpracticing.service.IQuizreviewService;
import java.util.Random;

/**
 *
 * @author Lenovo
 */
@Controller
public class QuizHandleController {

    @Autowired
    ILessonService service;

    @Autowired
    IQuizreviewService quizservice;

    @Autowired
    IQuizreviewQuestionService answerservice;

    @GetMapping("/quiz-handle")
    public String quizShow(@RequestParam Map<String, String> parameters, Model model, HttpSession session) {
        int id = Integer.parseInt(parameters.get("id"));
        int lessonId = 0;
        int countQuiz = 0;
        if (session.getAttribute("countQuiz") != null) {
            countQuiz = Integer.parseInt(session.getAttribute("countQuiz").toString());
        }
        if (session.getAttribute("lessonID") != null) {
            lessonId = Integer.parseInt(session.getAttribute("lessonID").toString());
        }
        int qid = Integer.parseInt(parameters.get("qid"));
        LessonDTO lesson = service.findById(id);
        model.addAttribute("les", lesson);
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("userSession", user);
        if (lessonId != id) {
            countQuiz = quizservice.getAllQuiz().size() + 1;
            session.setAttribute("countQuiz", countQuiz);
            QuizreviewDTO quiz = new QuizreviewDTO(countQuiz, lesson, user, null, null);
            quizservice.addQuiz(quiz);
        }
        session.setAttribute("lessonID", id);
        List<QuestionDTO> questions = (List<QuestionDTO>) session.getAttribute("questions");
        List<QuestionDTO> tempQues = new ArrayList<>();
        if (questions == null) {
            List<QuestionDTO> quest = lesson.getQuestions();
            Random random = new Random();
            int[] numbers = new int[10];
            
            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt(quest.size());
                if(i == 0) {
                    int temp = 0;
                    numbers[i] = randomNumber;
                    for (QuestionDTO questionDTO : quest) {
                        if (temp == randomNumber) {
                            tempQues.add(questionDTO);
                        }
                        temp++;
                    }
                    continue;
                }
                for(int j = 0; j < numbers.length; j++) {
                    if(numbers[j] == randomNumber) {
                        randomNumber = -1;
                        break;
                    }
                }
                if(randomNumber == -1) {
                    i--;
                } else {
                    int temp = 0;
                    numbers[i] = randomNumber;
                    for (QuestionDTO questionDTO : quest) {
                        if (temp == randomNumber) {
                            tempQues.add(questionDTO);
                        }
                        temp++;
                    }
                }
                
                
            }
            session.setAttribute("questions", tempQues);
            questions = tempQues;
        }
        int count = 0;
        for (QuestionDTO question : questions) {
            System.out.println(question.getId());
            if (count == qid) {
                session.setAttribute("qid", qid);
                if (answerservice.getUserAnswerByID(question.getId(), countQuiz) != null) {
                    QuizreviewQuestionDTO answer = answerservice.getUserAnswerByID(question.getId(), countQuiz);
                    model.addAttribute("answered", answer.getUserAnswer());
                } else {
                    QuizreviewDTO quizreviewId = new QuizreviewDTO(countQuiz, lesson, user, null, null);
                    QuizreviewQuestionDTO answer = new QuizreviewQuestionDTO(quizreviewId, question, "answer", true, false, false);
                    answerservice.add(answer);
                    model.addAttribute("answered", null);
                }
                model.addAttribute("id", id);
                model.addAttribute("qid", qid);
                model.addAttribute("currentQuestion", question);
                model.addAttribute("currentAnswer", question.getAnswer());
                model.addAttribute("size", questions.size() - 1);

                break;
            }
            count++;

        }
        return "customer/quizhandle";
    }

    @PostMapping("/quiz-handle")
    public String SaveAnswer(HttpServletRequest req, Model model, HttpSession session) {
        int id = Integer.parseInt(session.getAttribute("lessonID").toString());
        int qid = Integer.parseInt(session.getAttribute("qid").toString());
        String answerId = req.getParameter("optionGroup");
        LessonDTO lesson = service.findById(id);
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<QuestionDTO> questions = (List<QuestionDTO>) session.getAttribute("questions");
        int count = 0;
        int countQuiz = Integer.parseInt(session.getAttribute("countQuiz").toString());
        for (QuestionDTO question : questions) {
            if (count == qid) {
                List<AnswerDTO> answers = question.getAnswer();
                for (AnswerDTO ans : answers) {
                    if (ans.getExplanation().equals(answerId) && ans.getCheck() == true) {
                        QuizreviewDTO quizreviewId = new QuizreviewDTO(countQuiz, lesson, user, null, null);
                        QuizreviewQuestionDTO answer = new QuizreviewQuestionDTO(quizreviewId, question, answerId, true, false, true);
                        answerservice.update(answer);
                        break;
                    } else if (ans.getExplanation().equals(answerId) && ans.getCheck() == false) {
                        QuizreviewDTO quizreviewId = new QuizreviewDTO(countQuiz, lesson, user, null, null);
                        QuizreviewQuestionDTO answer = new QuizreviewQuestionDTO(quizreviewId, question, answerId, true, false, false);
                        answerservice.update(answer);
                        break;
                    }
                }

            }
            count++;
        }
        return "redirect:/quiz-handle?id=" + id + "&qid=" + qid;
    }

    @GetMapping("/submit")
    public String submitQuiz(@RequestParam Map<String, String> parameters, HttpSession session, Model model) {
        int id = Integer.parseInt(parameters.get("id"));
        int qid = Integer.parseInt(parameters.get("qid"));
        int count = 0;
        int countQuiz = Integer.parseInt(session.getAttribute("countQuiz").toString());
        List<QuizreviewQuestionDTO> answereds = answerservice.findById(countQuiz);
        for (QuizreviewQuestionDTO quizreviewQuestionDTO : answereds) {
            if (quizreviewQuestionDTO.getUserAnswer() != null) {
                count++;
            }

        }
        model.addAttribute("answeredQuestion", count);
        List<QuestionDTO> questions = (List<QuestionDTO>) session.getAttribute("questions");
        model.addAttribute("allQuestions", questions.size());
        model.addAttribute("qvid", session.getAttribute("countQuiz"));
        model.addAttribute("id", id);
        model.addAttribute("qid", qid);
        return "/customer/submitQuiz";
    }

}
