/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Subject;

import java.util.List;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Mosena
 */
@Repository
public interface ISubcategoryRepository 
        extends JpaRepository<Subcategory,Integer> {
    @Override
    public Subcategory save(Subcategory sc);
    public List<Subcategory> findSubcategoriesByCategory_Id(Integer id);
    public List<Subcategory> findBySubjects_Id(Integer subjectId);

    @Query(value = "select * from iquiz.subcategory where ", nativeQuery = true)
    public Subcategory findBySubjects(Subject subject);

    Subcategory findById(int id);
    
    @Query("select s from Subcategory s where s.category.id = :id")
    public List<Subcategory> findByCategory(@Param("id")Integer categoryId);
}
