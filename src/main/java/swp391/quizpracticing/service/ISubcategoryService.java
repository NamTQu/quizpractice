/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import swp391.quizpracticing.dto.SubcategoryDTO;
import swp391.quizpracticing.model.Subcategory;

import java.util.List;
import swp391.quizpracticing.dto.SubcategoryDTO;

/**
 *
 * @author Mosena
 */
public interface ISubcategoryService {

//    public List<Subcategory> getAllBySubjects(List<Subject> subjectList);

    public List<Subcategory> getAll();

    public Subcategory getById(int id);

    public List<Subcategory> findbySubId(Integer id);
    public List<Subcategory> listAll();
    public List<SubcategoryDTO> findByCategoryId(Integer categoryId);

}
