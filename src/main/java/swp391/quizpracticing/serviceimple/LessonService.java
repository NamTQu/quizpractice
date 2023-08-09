/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.LessonProperties;
import swp391.quizpracticing.repository.ILessonPropertiesRepository;
import swp391.quizpracticing.repository.ILessonRepository;
import swp391.quizpracticing.service.ILessonService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mosena
 */
@Service
public class LessonService implements ILessonService {
    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private ILessonRepository iLessonRepository;
    @Autowired
    private ILessonPropertiesRepository iLessonPropertiesRepository;

    private LessonDTO convertEntityToDTO(Lesson entity){
        return modelMapper.map(entity,LessonDTO.class);
    }

    @Override
    public LessonDTO findById(int id) {
        Lesson lesson = iLessonRepository.findById(id);
        LessonDTO result = convertEntityToDTO(lesson);
        return result;
    }

    @Override
    public Lesson findId(int id) {
        Lesson lesson = iLessonRepository.findById(id);
        return lesson;
    }
    @Override
    public List<LessonDTO> findAllSimulationExamsBySubjectId(Integer id) {
        List<Lesson> lessonsBySubjectId = iLessonRepository.findAllBySubjectId(id, 1);
        List<LessonDTO> result = new ArrayList<>();
        for(Lesson lesson : lessonsBySubjectId) {
            LessonDTO lessonDTO = convertEntityToDTO(lesson);
            result.add(lessonDTO);
        }
        return result;
    }

    @Override
    public List<LessonDTO> searchByExamName(String examName) {
        List<Lesson> foundLessons = iLessonRepository.findByNameContainingIgnoreCase(examName);
        List<LessonDTO> result = new ArrayList<>();
        for(Lesson lesson : foundLessons) {
            LessonDTO lessonDTO = convertEntityToDTO(lesson);
            result.add(lessonDTO);
        }
        return result;
    }

    @Override
    public List<LessonProperties> findByLessonId(Integer lessonId) {
        return iLessonPropertiesRepository.findByLesson_Id(lessonId);
    }
    @Override
    public Integer numbersByLesson(Integer lesId){
        int num=0;
        for (LessonProperties lessonProperties: findByLessonId(lesId)){
            num+= lessonProperties.getNoQuestion();
        }
        return num;
    }
    @Override
    public List<Integer> numberOfQuestion() {
        List<Integer> list=new ArrayList<>();
        for (Lesson lesson: lessonAll()){
            int num=0;
            for (LessonProperties lessonProperties: findByLessonId(lesson.getId())){
                num+= lessonProperties.getNoQuestion();
            }
            list.add(num);
        }
        return list;
    }

    @Override
    public  List<LessonDTO> listAll(){
        List<Lesson> lessons=iLessonRepository.findAll();
        List<LessonDTO> lessonDTOS=new ArrayList<>();
        for (Lesson lesson: lessons){
            LessonDTO lessonDTO=convertEntityToDTO(lesson);
            lessonDTOS.add(lessonDTO);
        }
        return lessonDTOS;
    }

    @Override
    public  List<LessonDTO> listAllQuiz(Integer id){
        List<Lesson> lessons=iLessonRepository.findAllByLessonType(id);
        List<LessonDTO> lessonDTOS=new ArrayList<>();
        for (Lesson lesson: lessons){
            LessonDTO lessonDTO=convertEntityToDTO(lesson);
            lessonDTOS.add(lessonDTO);
        }
        return lessonDTOS;
    }

    @Override
    public Page<Lesson> getLessons(int pageNo, int pageSize, String searchValue, Integer subjectId, Integer quizTypeId, String sortBy, String order) {
        Pageable page;
        if(order.equals("asc")){
            page = PageRequest.of(pageNo - 1, pageSize,
                    Sort.by(sortBy).ascending());
        }
        else{
            page = PageRequest.of(pageNo - 1, pageSize,
                    Sort.by(sortBy).descending());
        }
        Specification<Lesson> specification=(root, query, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();
            if(subjectId!=null){
                predicates.add(criteriaBuilder.equal(root.get("subject")
                        .get("id"), subjectId));
            }
            if(quizTypeId!=null){
                predicates.add(criteriaBuilder.equal(root.get("testType")
                        .get("id"), quizTypeId));
            }

            if(searchValue!=null){
                String searchPattern = "%" + searchValue + "%";
                String searchBy="name";
                predicates.add(criteriaBuilder.like(root
                        .get(searchBy), searchPattern));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
        return iLessonRepository.findAll(specification,page);
    }
    @Override
    public void save(Lesson lesson){
        iLessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> lessonAll(){
        return iLessonRepository.findAll();
    }
    @Override
    public void delete(Integer id){
        iLessonRepository.deleteById(id);
    }
}
