/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.AnswerDTO;
import swp391.quizpracticing.model.Answer;
import swp391.quizpracticing.service.IAnswerService;

/**
 *
 * @author Mosena
 */
@Service
public class AnswerService implements IAnswerService {
    @Autowired
    private ModelMapper modelMapper;
    
    private AnswerDTO convertEntityToDTO(Answer entity) {
        return modelMapper.map(entity, AnswerDTO.class);
    }
}
