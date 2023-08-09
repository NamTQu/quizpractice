/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.UserSubject;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Mosena
 */
@Repository
public interface IUserSubjectRepository
        extends JpaRepository<UserSubject,Integer>,
        JpaSpecificationExecutor<UserSubject> {

    @Query(value = "select * from iquiz.user_subject where user_id = :user_id and registration_status_id = :registration_status_id", nativeQuery = true)
    public List<UserSubject> getAllByUserIdAndRegistrationStatus(@Param("user_id") Integer userId, @Param("registration_status_id") Integer registrationId);

    //Nam's code
    @Override
    public Page<UserSubject> findAll(Specification specification,Pageable page);
    
    @Override
    public UserSubject save(UserSubject us);

    @Override
    public UserSubject getReferenceById(Integer id);
    public  List<UserSubject> findAllBySubjectIn(List<Subject> subjects);
    public List<UserSubject> findUserSubjectsByRegistrationTimeBetween(Timestamp timeStart, Timestamp timeEnd);
    public List<UserSubject> findAllBySubjectIdAndUserId(Integer subjectId, Integer userId);

}
