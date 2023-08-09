/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import swp391.quizpracticing.model.Dimension;

/**
 *
 * @author Mosena
 */
@Repository
public interface IDimensionRepository extends JpaRepository<Dimension,Integer> {
    @Override
    public Dimension save(Dimension d);

    @Override
    List<Dimension> findAll();

    @Override
    Dimension getById(Integer integer);

    @Override
    List<Dimension> findAllById(Iterable<Integer> integers);

    public Long countById(Integer id);

    List<Dimension> findBySubjects_Id(Integer subjectId);
    
    List<Dimension> getDimensionByName(String name);
}
