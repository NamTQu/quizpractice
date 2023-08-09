/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.LessontypeDTO;
import swp391.quizpracticing.model.Lessontype;
import swp391.quizpracticing.service.ILessontypeService;

/**
 *
 * @author Mosena
 */
@Service
public class LessontypeService implements ILessontypeService {
    @Autowired
    private ModelMapper modelMapper;
    
    private LessontypeDTO convertEntityToDTO(Lessontype entity){
        return modelMapper.map(entity,LessontypeDTO.class);
    }
}
