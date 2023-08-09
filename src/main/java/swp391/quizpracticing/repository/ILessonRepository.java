/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.User;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface ILessonRepository extends JpaRepository<Lesson,Integer>, JpaSpecificationExecutor<Lesson> {

    @Override
    public Page<Lesson> findAll(Specification spec, Pageable pageable);
    public Lesson findById(int id);

    public  List<Lesson> findAllByLessonType(Integer id);

    @Query(value = "select * from iquiz.lesson where subject_id = :subject_id and test_type_id = :test_type_id",
            nativeQuery = true)
    public List<Lesson> findAllBySubjectId(@Param("subject_id") Integer subjectId, @Param("test_type_id")
    Integer testTypeId);

    public List<Lesson> findByNameContainingIgnoreCase(String examName);
    
}
