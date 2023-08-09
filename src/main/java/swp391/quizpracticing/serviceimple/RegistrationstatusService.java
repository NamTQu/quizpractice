/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.RegistrationstatusDTO;
import swp391.quizpracticing.model.Registrationstatus;
import swp391.quizpracticing.repository.IRegistrationstatusRepository;
import swp391.quizpracticing.service.IRegistrationstatusService;

/**
 *
 * @author Mosena
 */
@Service
public class RegistrationstatusService implements IRegistrationstatusService {
    
    @Autowired
    private IRegistrationstatusRepository registrationStatusRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    private RegistrationstatusDTO convertEntityToDTO(Registrationstatus entity){
        return modelMapper.map(entity,
                RegistrationstatusDTO.class);
    }

    @Override
    public List<RegistrationstatusDTO> findAll() {
        return registrationStatusRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RegistrationstatusDTO getById(Integer id) {
        return convertEntityToDTO(registrationStatusRepository
                .getReferenceById(id));
    }

    @Override
    public List<Registrationstatus> findAllEntity() {
        return registrationStatusRepository.findAll();
    }
}
