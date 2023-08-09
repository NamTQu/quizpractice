/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import swp391.quizpracticing.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import swp391.quizpracticing.dto.ImportDataDTO;

/**
 *
 * @author Mosena
 */
public interface IQuestionService {
    public Question getQuestionsById(Integer id);
    public List<Question> getBySubCategory(Integer id);
    public List<Question> getRandomBySubCategories(Integer id, Integer number);
    public void save(Question q);
    public List<Question> getQuestionByLessonAndSub(Integer lessonId, Integer subCategoryId);
    public Page<Question> findPaginated(Pageable pageable,List<Question> listQuestion);
    
    public void importQuestion(List<ImportDataDTO> list) throws Exception;
    
}
