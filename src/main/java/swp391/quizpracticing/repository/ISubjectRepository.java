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
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISubjectRepository extends JpaRepository<Subject,Integer>,
        JpaSpecificationExecutor<Subject>{
    @Override
    public Page<Subject> findAll(Specification specification,Pageable pageable);

    public List<Subject> findAllByFeatured(Boolean isFeatured);

    public Subject findByLessons_Id(Integer id);
    public Page<Subject> findByStatus(Boolean status, Pageable pageable);

    @Override
    public List<Subject> findAll();
    public List<Subject> findByBriefInfoContaining(String s);

    @Query(value = "select * from iquiz.subject", nativeQuery = true)
    public Page<Subject> findAllSubjectsPaginated(Pageable pageable);

    public Subject findById(int id);

    @Override
    Subject getById(Integer integer);

    @Override
    List<Subject> findAllById(Iterable<Integer> integers);

    @Query(value = "select * from iquiz.subject where owner_id = :owner_id", nativeQuery = true)
    public Page<Subject> findByOwnerId(@Param("owner_id") Integer id, Pageable pageable);

    @Query(value = "select * from iquiz.subject where owner_id = :owner_id and brief_info like %:searchTerm%", nativeQuery = true)
    public Page<Subject> findByOwnerIdAndName(@Param("owner_id") Integer id, @Param("searchTerm") String searchTerm, Pageable pageable);

    @Query(value = "select * from iquiz.subject where owner_id = :owner_id and status = :status", nativeQuery = true)
    public Page<Subject> findByOwnerIdAndStatus(@Param("owner_id") Integer id, @Param("status") Integer status, Pageable pageable);

    @Query(value="Select * from `subject` s "
            + "join category c "
            + "on s.idCategory=c.id "
            + "where c.id = ?1", nativeQuery = true)
    public Page<Subject> findByCategory(Pageable pageable, int categoryId);

    @Query(value="Select * from `subject` s "
            + "where s.course_name like %?1%",nativeQuery = true)
    public Page<Subject> searchSubjectName(Pageable pageable,
                                           String subjectName);

    @Query(value="Select * from `subject` s "
            + "join category c "
            + "on s.idCategory=c.id "
            + "where s.course_name like %?1% && c.id = ?2", nativeQuery = true)
    public Page<Subject> searchSubjectNameAndCategory(Pageable pageable,
                                                      String subjectName, int categoryId);

    public Page<Subject> findByBriefInfoContainingIgnoreCase(String searchTerm,
                                                             Pageable pageable);

    boolean existsSubjectByBriefInfo(String briefInfo);

    @Query(value = "select * from iquiz.subject where owner_id = :owner_id", nativeQuery = true)
    List<Subject> findByExpertId(@Param("owner_id") Integer id);

    public Long countById(Integer id);

}