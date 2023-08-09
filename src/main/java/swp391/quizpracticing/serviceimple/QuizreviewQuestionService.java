package swp391.quizpracticing.serviceimple;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.model.QuizreviewQuestion;
import swp391.quizpracticing.repository.IQuizreviewQuestionRepository;
import swp391.quizpracticing.service.IQuizreviewQuestionService;

import java.util.List;
import org.modelmapper.ModelMapper;
import swp391.quizpracticing.dto.AnswerQuestionDTO;
import swp391.quizpracticing.dto.QuizReviewResponse;
import swp391.quizpracticing.dto.QuizreviewQuestionDTO;

@Service
public class QuizreviewQuestionService implements IQuizreviewQuestionService {

    @Autowired
    private IQuizreviewQuestionRepository iQuizreviewQuestionRepository;

    @Autowired
    private ModelMapper modelMapper;

    private QuizreviewQuestionDTO convertEntityToDTO(QuizreviewQuestion entity) {
        return modelMapper.map(entity, QuizreviewQuestionDTO.class);
    }

    @Override
    public List<QuizreviewQuestion> getAllByQuizreviewId(Integer id) {
        return iQuizreviewQuestionRepository.getAllByQuizreviewId(id);
    }

    @Override
    public Integer getNumberOfCorrectAnswerByQuizreviewId(Integer quizreviewId, Boolean isCorrect) {
        return iQuizreviewQuestionRepository.getNumberOfCorrectAnswer(quizreviewId, isCorrect);
    }

    @Override
    public Integer getNumberOfQuestionsByQuizreviewId(Integer quizreviewId) {
        return iQuizreviewQuestionRepository.getNumberOfQuestionsOfAQuizReview(quizreviewId);
    }

    @Override
    public List<QuizReviewResponse> getAllByQuizreviewResponse(Integer id, Integer user_id) {
        return iQuizreviewQuestionRepository.getAllByQuizreviewResponse(id, user_id);
    }

    @Override
    public List<AnswerQuestionDTO> getAnswerByIdQuestion(Integer id) {
        return iQuizreviewQuestionRepository.getAnswerByIdQuestion(id);
    }

    @Override
    public QuizreviewQuestionDTO getUserAnswerByID(int qid, int id) {
        if (iQuizreviewQuestionRepository.getUserAnswer(qid, id) == null) {
            return null;
        } else {
            return modelMapper.map(iQuizreviewQuestionRepository.getUserAnswer(qid, id), QuizreviewQuestionDTO.class);
        }
    }

    @Override
    public void add(QuizreviewQuestionDTO answer) {
        iQuizreviewQuestionRepository.addAnswer(answer.getQuizreviewId().getId(), answer.getQuestionId().getId(), answer.getBookmark(), answer.getStatus(), answer.getIs_correct(), answer.getUserAnswer());
    }

    @Override
    public void update(QuizreviewQuestionDTO answer) {
        iQuizreviewQuestionRepository.updateAnswer(answer.getQuizreviewId().getId(), answer.getQuestionId().getId(), answer.getUserAnswer(), answer.getIs_correct(), answer.getBookmark(), answer.getStatus());
    }

    @Override
    public List<QuizreviewQuestionDTO> findById(int countQuiz) {
        List<QuizreviewQuestion> reviews = iQuizreviewQuestionRepository.findByReviewId(countQuiz);
        List<QuizreviewQuestionDTO> temps = new ArrayList<>();
        for (QuizreviewQuestion quizreviewQuestion : reviews) {
            QuizreviewQuestionDTO temp = convertEntityToDTO(quizreviewQuestion);
            temps.add(temp);
        }
        return temps;
    }
}

