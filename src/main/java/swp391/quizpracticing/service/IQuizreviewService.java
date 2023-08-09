/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.QuizreviewDTO;
import swp391.quizpracticing.model.Quizreview;
import swp391.quizpracticing.model.Subject;

import java.util.List;

/**
 *
 * @author Mosena
 */
public interface IQuizreviewService {

    public Page<Quizreview> getAllQuizreviewsByUserId(Integer userId, int page);

    public List<QuizreviewDTO> findByLessonId(Integer lessonId);

    public Page<Quizreview> findByLessonName(String searchTerm, Integer userId, int page);

    public Quizreview findById(Integer quizID);

    public List<QuizreviewDTO> getAllQuiz();

    public void addQuiz(QuizreviewDTO quiz);

    public Subject getSubjectByQuizreviewId(Integer id);

}
