/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import swp391.quizpracticing.dto.BlogcategoryDTO;
import swp391.quizpracticing.model.Blogcategory;

import java.util.List;

/**
 *
 * @author Mosena
 */
public interface IBlogcategoryService {
    public Blogcategory findByPostCategoryId(int id);

    public List<BlogcategoryDTO> getAllCategories();

}
