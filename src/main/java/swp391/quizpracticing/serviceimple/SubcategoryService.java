/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.SubcategoryDTO;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.repository.ISubcategoryRepository;

import swp391.quizpracticing.service.ISubcategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mosena
 */
@Service
public class SubcategoryService implements ISubcategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISubcategoryRepository iSubcategoryRepository;

    private SubcategoryDTO convertEntityToDTO(Subcategory entity){
        return modelMapper.map(entity,SubcategoryDTO.class);
    }

    @Override
    public List<Subcategory> getAll() {
        return iSubcategoryRepository.findAll();
    }

    @Override
    public Subcategory getById(int id) {
        return iSubcategoryRepository.findById(id);
    }

    @Override
    public List<Subcategory> findbySubId(Integer id) {
        return iSubcategoryRepository.findBySubjects_Id(id);
    }

    @Override
    public List<Subcategory> listAll() {
        return iSubcategoryRepository.findAll();
    }

    @Override
    public List<SubcategoryDTO> findByCategoryId(Integer categoryId) {
        return iSubcategoryRepository.findByCategory(categoryId)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }
}
