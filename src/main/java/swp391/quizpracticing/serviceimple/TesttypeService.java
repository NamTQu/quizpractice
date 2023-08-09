/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.TesttypeDTO;
import swp391.quizpracticing.model.Testtype;
import swp391.quizpracticing.repository.ITesttypeRepository;
import swp391.quizpracticing.service.ITesttypeService;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Service
public class TesttypeService implements ITesttypeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ITesttypeRepository iTesttypeRepository;

    private TesttypeDTO convertEntityToDTO(Testtype entity){
        return modelMapper.map(entity,TesttypeDTO.class);
    }

    @Override
    public List<Testtype> listAll() {
        return iTesttypeRepository.findAll();
    }
}
