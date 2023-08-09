/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.QuestionDTO;
import swp391.quizpracticing.model.Question;
import swp391.quizpracticing.repository.IQuestionRepository;
import swp391.quizpracticing.service.IQuestionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import swp391.quizpracticing.Utils.Utility;
import swp391.quizpracticing.dto.ImportDataDTO;
import swp391.quizpracticing.model.Answer;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.Level;
import swp391.quizpracticing.repository.IAnswerRepository;
import swp391.quizpracticing.repository.IDimensionRepository;
import swp391.quizpracticing.repository.ILevelRepository;

/**
 *
 * @author Mosena
 */
@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IQuestionRepository iQuestionRepository;
    
    @Autowired
    private ILevelRepository iLevelRepository;
    
    @Autowired
    private IDimensionRepository iDimensionRepository;
    
    @Autowired
    private IAnswerRepository iAnswerRepository;
    
    private QuestionDTO convertEntityToDTO(Question entity){
        return modelMapper.map(entity,QuestionDTO.class);
    }

    @Override
    public Question getQuestionsById(Integer id) {
        return iQuestionRepository.getQuestionsById(id);
    }

    @Override
    public List<Question> getBySubCategory(Integer id) {
        return iQuestionRepository.findBySubCategories_Id(id);
    }

    @Override
    public List<Question> getRandomBySubCategories(Integer id, Integer number){
        List<Question> questions = new ArrayList<>();
        List<Question> randomQues= iQuestionRepository.findBySubCategories_Id(id);
        for (int i=0; i<number; i++){
            Random random = new Random();

            int randomNumber = random.nextInt(randomQues.size()-1);
            System.out.println(randomNumber);
            questions.add(randomQues.get(0));
        }

        return questions;
    }


    @Override
    public void save(Question q) {
        iQuestionRepository.save(q);
    }

    @Override
    public List<Question> getQuestionByLessonAndSub(Integer lessonId, Integer subCategoryId) {
        List<Question> q = new ArrayList<>();
        List<Question> questionLessonList=iQuestionRepository.findByLessons_Id(lessonId);
        List<Question> questionSubList=iQuestionRepository.findBySubCategories_Id(subCategoryId);
        for (Question qles: questionLessonList){
            for (Question question: questionSubList){
                if (Objects.equals(question.getId(), qles.getId()))
                    q.add(question);
            }
        }
        return q;
    }

    public Page<Question> findPaginated(Pageable pageable,List<Question> listQuestion) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Question> list;

        if (listQuestion.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, listQuestion.size());
            list = listQuestion.subList(startItem, toIndex);
        }

        Page<Question> bookPage
                = new PageImpl<Question>(list, PageRequest.of(currentPage, pageSize), listQuestion.size());

        return bookPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importQuestion(List<ImportDataDTO> list) throws Exception {
        if (list.size() > 0 && list != null) {
            for (ImportDataDTO dto : list) {
                String levelName = dto.getLevel().trim().toLowerCase();
                String dimensionName = dto.getDimension().trim().toLowerCase();

                // check anwser valid
                String checkAnswer1 = Utility.checkAnswerValid(dto.getAnswer1());
                String checkAnswer2 = Utility.checkAnswerValid(dto.getAnswer2());
                String checkAnswer3 = Utility.checkAnswerValid(dto.getAnswer3());
                String checkAnswer4 = Utility.checkAnswerValid(dto.getAnswer4());

                if(checkAnswer1.isEmpty() && checkAnswer2.isEmpty() && checkAnswer3.isEmpty() && checkAnswer4.isEmpty()){
                    throw new Exception("Error");
                }
                // get level and dimension
                List<Level> levels = iLevelRepository.getLevelByName(levelName);
                // Get the Level object whose name matches the levelName
                Level level = levels.stream()
                        .filter(l -> l.getName().toLowerCase().trim().equals(levelName))
                        .findFirst()
                        .orElse(null);

                List<Dimension> dimensions = iDimensionRepository.getDimensionByName(dto.getDimension().toLowerCase().trim());

                Dimension dimension = dimensions.stream()
                        .filter(d -> d.getName().toLowerCase().trim().equals(dimensionName))
                        .findFirst()
                        .orElse(null);

                if (level == null && dimension == null) {
                    throw new Exception("Error");
                }

                Question question = new Question(dto.getContent(), true, "", "", "", dimension, level);
                iQuestionRepository.save(question);

                Question q = iQuestionRepository.findTopByOrderByIdDesc();

                // insert answer
                String a1 = checkAnswer1.isEmpty() ? dto.getAnswer1() : checkAnswer1;
                String a2 = checkAnswer2.isEmpty() ? dto.getAnswer2() : checkAnswer2;
                String a3 = checkAnswer3.isEmpty() ? dto.getAnswer3() : checkAnswer3;
                String a4 = checkAnswer4.isEmpty() ? dto.getAnswer4() : checkAnswer4;

                List<Answer> answerList = new ArrayList<>();
                answerList.add(new Answer(a1, checkAnswer1.isEmpty() ? false : true, true, q));
                answerList.add(new Answer(a2, checkAnswer2.isEmpty() ? false : true, true, q));
                answerList.add(new Answer(a3, checkAnswer3.isEmpty() ? false : true, true, q));
                answerList.add(new Answer(a4, checkAnswer4.isEmpty() ? false : true, true, q));

                iAnswerRepository.saveAll(answerList);

            }
        }
    }

    
    
}
