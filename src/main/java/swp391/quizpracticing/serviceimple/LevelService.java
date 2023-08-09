/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.LevelDTO;
import swp391.quizpracticing.model.Level;
import swp391.quizpracticing.repository.ILevelRepository;
import swp391.quizpracticing.service.ILevelService;

import java.util.List;

/**
 *
 * @author Mosena
 */
@Service
public class LevelService implements ILevelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ILevelRepository iLevelRepository;
    private LevelDTO convertEntityToDTO(Level entity){
        return modelMapper.map(entity,LevelDTO.class);
    }

    @Override
    public List<Level> listAll() {
        return iLevelRepository.findAll();
    }
}
