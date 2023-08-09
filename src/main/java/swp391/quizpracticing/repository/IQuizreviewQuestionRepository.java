/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.QuizreviewQuestion;
import swp391.quizpracticing.model.QuizreviewQuestionKey;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;

/**
 *
 * @author Mosena
 */
@Repository
public interface IQuizreviewQuestionRepository extends JpaRepository<QuizreviewQuestion, QuizreviewQuestionKey> {

    @Query(value = "select * from iquiz.quizreview_question where quizreview_id = :quizreview_id", nativeQuery = true)
    public List<QuizreviewQuestion> getAllByQuizreviewId(@Param("quizreview_id") Integer quizreviewId);

    @Query(value = "select count(is_correct) from iquiz.quizreview_question where quizreview_id = :quizreview_id and is_correct = :is_correct", nativeQuery = true)
    public Integer getNumberOfCorrectAnswer(@Param("quizreview_id") Integer quizreviewId, @Param("is_correct") Boolean isCorrect);

    @Query(value = "select count(*) from iquiz.quizreview_question where quizreview_id = :quizreview_id", nativeQuery = true)
    public Integer getNumberOfQuestionsOfAQuizReview(@Param("quizreview_id") Integer quizreviewId);

    @Query(value = """
                   select * from   ( SELECT iquiz.question.*,
                     (SELECT bookmark FROM iquiz.quizreview_question WHERE question_id = iquiz.question.id AND quizreview_id = :id) AS bookmark,
  COALESCE(
                         (SELECT user_answer FROM iquiz.quizreview_question WHERE user_answer not IN 
                           (SELECT explanation FROM iquiz.answer
                           WHERE question_id = iquiz.question.id AND checking = 1)
                           AND question_id = iquiz.question.id AND quizreview_id = :id),
                         'true') AS checking
                   FROM iquiz.question
                   WHERE id IN (
                     SELECT question_id FROM iquiz.lesson_question WHERE
                     lesson_id = (SELECT lesson_id FROM iquiz.quizreview WHERE id = :id AND user_id = :user_id)
                   ) order by id) as res  where res.bookmark is not null;
                   """, nativeQuery = true)
    public List<QuizReviewResponse> getAllByQuizreviewResponse(@Param("id") Integer id, @Param("user_id") Integer user_id );

    @Query(value = """
                  select * from iquiz.answer where question_id =:id
                   """, nativeQuery = true)
    public List<AnswerQuestionDTO> getAnswerByIdQuestion(@Param("id") Integer id);

    @Query(value = "select * from iquiz.quizreview_question where question_id = :question_id and quizreview_id = :quizreview_id", nativeQuery = true)
    public QuizreviewQuestion getUserAnswer(@Param("question_id") Integer question_id, @Param("quizreview_id") Integer quizreview_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO iquiz.quizreview_question (quizreview_id, question_id, status, is_correct, bookmark, user_answer) VALUES (:quizreview_id, :question_id, :status, :is_correct, :bookmark, :answer)", nativeQuery = true)
    void addAnswer(@Param("quizreview_id") Integer quizreview_id, @Param("question_id") Integer question_id, @Param("status") boolean status, @Param("is_correct") boolean is_correct, @Param("bookmark") boolean  bookmark, @Param("answer") String answer);

    @Modifying
    @Transactional
    @Query(value = "UPDATE iquiz.quizreview_question SET is_correct = :is_correct, user_answer = :useranswer , bookmark = :bookmark, status = :status  WHERE (question_id = :question_id) and (quizreview_id =  :quizreview_id);", nativeQuery = true)
    public void updateAnswer(@Param("quizreview_id") Integer quizreview_id, @Param("question_id") Integer question_id, @Param("useranswer") String useranswer, @Param("is_correct") Boolean is_correct, @Param("bookmark") Boolean bookmark, @Param("status") Boolean status);

    @Query(value = "SELECT * FROM iquiz.quizreview_question where quizreview_id = :quizreview_id", nativeQuery = true)
    public List<QuizreviewQuestion> findByReviewId(@Param("quizreview_id") Integer quizreview_id);

}
