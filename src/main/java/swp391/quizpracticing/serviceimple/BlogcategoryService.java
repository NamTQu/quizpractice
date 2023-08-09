/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.BlogcategoryDTO;
import swp391.quizpracticing.model.Blogcategory;
import swp391.quizpracticing.repository.IBlogcategoryRepository;
import swp391.quizpracticing.service.IBlogcategoryService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mosena
 */
@Service
public class BlogcategoryService implements IBlogcategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBlogcategoryRepository iBlogCategoryRepository;

    private BlogcategoryDTO convertEntityToDTO(Blogcategory entity){
        return modelMapper.map(entity,BlogcategoryDTO.class);
    }

    @Override
    public Blogcategory findByPostCategoryId(int id) {
        return iBlogCategoryRepository.findById(id);
    }

    @Override
    public List<BlogcategoryDTO> getAllCategories() {
        List<BlogcategoryDTO> results = new ArrayList<>();
        List<Blogcategory> posts = iBlogCategoryRepository.findAll();
        for(Blogcategory post : posts) {
            BlogcategoryDTO categoryDTO = new BlogcategoryDTO();
            categoryDTO.entityToDTO(post);
            results.add(categoryDTO);
        }
        return results;
    }

}
