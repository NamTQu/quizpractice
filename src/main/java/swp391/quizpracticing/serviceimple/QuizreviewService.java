/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.QuizreviewDTO;
import swp391.quizpracticing.model.Quizreview;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.repository.IQuizreviewRepository;
import swp391.quizpracticing.service.IQuizreviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mosena
 */
@Service
public class QuizreviewService implements IQuizreviewService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IQuizreviewRepository iQuizreviewRepository;

    private QuizreviewDTO convertEntityToDTO(Quizreview entity) {
        return modelMapper.map(entity, QuizreviewDTO.class);
    }

    @Override
    public Page<Quizreview> getAllQuizreviewsByUserId(Integer userId, int page) {
        return iQuizreviewRepository.findAllByUserId(userId, PageRequest.of(page, 4));
    }

    @Override
    public List<QuizreviewDTO> findByLessonId(Integer lessonId) {
        List<Quizreview> findByLessonId = iQuizreviewRepository.findAllByLessonId(lessonId);
        List<QuizreviewDTO> results = new ArrayList<>();

        for (Quizreview quiz : findByLessonId) {
            QuizreviewDTO quizreviewDTO = convertEntityToDTO(quiz);
            results.add(quizreviewDTO);
        }

        return results;
    }

    @Override
    public Page<Quizreview> findByLessonName(String searchTerm, Integer userId, int page) {
        return iQuizreviewRepository.findAllByLessonName(searchTerm, userId, PageRequest.of(page, 4));
    }

    @Override
    public Quizreview findById(Integer quizID) {
        Optional<Quizreview> acc = iQuizreviewRepository.findById(quizID);
        return acc.isPresent() ? acc.get() : null;
    }

    @Override
    public List<QuizreviewDTO> getAllQuiz() {
        List<Quizreview> quizs = iQuizreviewRepository.findAll();
        List<QuizreviewDTO> results = new ArrayList<>();

        for (Quizreview quiz : quizs) {
            QuizreviewDTO quizreviewDTO = convertEntityToDTO(quiz);
            results.add(quizreviewDTO);
        }
        return results;
    }

    @Override
    public void addQuiz(QuizreviewDTO quiz) {
        iQuizreviewRepository.save(modelMapper.map(quiz, Quizreview.class));
    }

    @Override
    public Subject getSubjectByQuizreviewId(Integer id) {

        return null;
    }

}
