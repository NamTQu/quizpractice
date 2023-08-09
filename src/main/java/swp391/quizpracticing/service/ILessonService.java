/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.LessonProperties;

import java.util.List;

/**
 *
 * @author Mosena
 */
public interface ILessonService {
    public List<LessonDTO> listAll();
    public List<LessonDTO> listAllQuiz(Integer id);
    public Page<Lesson> getLessons(int pageNo, int pageSize,
                                   String searchValue, Integer subjectId, Integer quizTypeId,
                                   String sortBy, String order);
    public LessonDTO findById(int id);
    public Lesson findId(int id);
    public void save(Lesson lesson);

    public List<LessonDTO> findAllSimulationExamsBySubjectId(Integer id);

    public List<LessonDTO> searchByExamName(String examName);
    public List<LessonProperties> findByLessonId(Integer lessonId);
    public Integer numbersByLesson(Integer les);
    public List<Integer> numberOfQuestion();
    public List<Lesson> lessonAll();
    public void delete(Integer id);
}
