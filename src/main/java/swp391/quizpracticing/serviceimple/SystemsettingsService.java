/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.SystemsettingsDTO;
import swp391.quizpracticing.model.Systemsettings;
import swp391.quizpracticing.service.ISystemsettingsService;

/**
 *
 * @author Mosena
 */
@Service
public class SystemsettingsService implements ISystemsettingsService {
    @Autowired
    private ModelMapper modelMapper;
    
    private SystemsettingsDTO convertEntityToDTO(Systemsettings entity){
        return modelMapper.map(entity,SystemsettingsDTO.class);
    }
}
