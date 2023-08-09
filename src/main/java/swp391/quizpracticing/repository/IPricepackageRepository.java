/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Pricepackage;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface IPricepackageRepository extends JpaRepository<Pricepackage,Integer> {
    @Query(value = "select * from iquiz.pricepackage where subject_id = ?1", nativeQuery = true)
    public List<Pricepackage> getPricepackageBySubjects(Integer id);

    @Override
    public Page<Pricepackage> findAll(Pageable pageable);

    @Override
    public List<Pricepackage> findAll();

    public Pricepackage findById(int subjectId);

    @Override
    Pricepackage getById(Integer integer);

    @Override
    List<Pricepackage> findAllById(Iterable<Integer> integers);

    @Query(value = "select * from iquiz.pricepackage where id = ?1", nativeQuery = true)
    Pricepackage findPricepackageById(Integer id);

    @Query("select p from Pricepackage p where p.subject.id= :id")
    public List<Pricepackage> getPricepackageBySubId(Integer id);
    
    public Long countById(Integer id);
}

