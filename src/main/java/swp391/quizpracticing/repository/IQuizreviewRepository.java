/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.Quizreview;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.User;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface IQuizreviewRepository extends JpaRepository<Quizreview,Integer> {

    @Query(value = "select * from iquiz.quizreview where user_id = :user_id order by id desc", nativeQuery = true)
    public Page<Quizreview> findAllByUserId(@Param("user_id") Integer userId, Pageable pageable);

    @Query(value = "select * from iquiz.quizreview where lesson_id = :lesson_id order by id desc", nativeQuery = true)
    public List<Quizreview> findAllByLessonId(@Param("lesson_id") Integer lessonId);

    @Query(value = "select q.id, q.date_taken, q.duration, q.lesson_id, q.user_id from iquiz.quizreview q join iquiz.lesson l on q.lesson_id = l.id where l.name like %?1% and q.user_id = ?2 order by id desc", nativeQuery = true)
    public Page<Quizreview> findAllByLessonName(String searchTerm, Integer userId, Pageable pageable);

}
