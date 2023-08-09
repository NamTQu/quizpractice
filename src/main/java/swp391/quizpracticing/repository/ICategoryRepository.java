/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Category;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {
    @Override
    public Category save(Category c);

    public Category findById(int id);
    
    @Override
    public List<Category> findAll();
}
