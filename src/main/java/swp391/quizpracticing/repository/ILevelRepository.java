/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Level;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Repository
public interface ILevelRepository extends JpaRepository<Level,Integer> {
    @Override
    public Level save(Level l);
    @Override
    public List<Level> findAll();
    
    public List<Level> getLevelByName(String name);
}
