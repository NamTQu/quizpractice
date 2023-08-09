/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Question;
import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface IQuestionRepository extends JpaRepository<Question,Integer> {
    Question getQuestionsById(Integer id);
    List<Question> findBySubCategories_Id (Integer subCategoryId);
    List<Question> findByLessons_Id(Integer lessonId);

    Question findTopByOrderByIdDesc();


}
