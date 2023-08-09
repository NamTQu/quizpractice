/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Blogcategory;

/**
 *
 * @author Mosena
 */
@Repository
public interface IBlogcategoryRepository 
        extends JpaRepository<Blogcategory,Integer> {
    @Override
    public Blogcategory save(Blogcategory b);

    Blogcategory findById(int id);
}
