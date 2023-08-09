/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Testtype;

/**
 *
 * @author Mosena
 */
@Repository
public interface ITesttypeRepository  extends JpaRepository<Testtype,Integer> {
    @Override
    public Testtype save(Testtype tt);
}
