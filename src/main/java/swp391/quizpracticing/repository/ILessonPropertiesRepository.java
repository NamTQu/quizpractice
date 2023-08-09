package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swp391.quizpracticing.model.LessonProperties;

import java.util.List;

public interface ILessonPropertiesRepository extends JpaRepository<LessonProperties, Integer> {
    List<LessonProperties> findByLesson_Id(Integer id);
    void deleteLessonPropertiesByLesson_Id(Integer lesson_id);

}
