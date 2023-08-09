package swp391.quizpracticing.service;

import swp391.quizpracticing.model.QuizreviewQuestion;

import java.util.List;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;
import swp391.quizpracticing.dto.QuizreviewQuestionDTO;

public interface IQuizreviewQuestionService {

    public List<QuizreviewQuestion> getAllByQuizreviewId(Integer id);

    public Integer getNumberOfCorrectAnswerByQuizreviewId(Integer quizreviewId, Boolean isCorrect);

    public Integer getNumberOfQuestionsByQuizreviewId(Integer quizreviewId);

    public List<QuizReviewResponse> getAllByQuizreviewResponse(Integer id, Integer user_id);

    public List<AnswerQuestionDTO> getAnswerByIdQuestion(Integer id);

    public QuizreviewQuestionDTO getUserAnswerByID(int qid, int id);

    public void add(QuizreviewQuestionDTO answer);

    public void update(QuizreviewQuestionDTO answer);

    public List<QuizreviewQuestionDTO> findById(int countQuiz);

}
