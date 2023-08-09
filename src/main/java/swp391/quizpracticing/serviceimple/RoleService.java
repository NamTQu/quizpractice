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
import swp391.quizpracticing.dto.RoleDTO;
import swp391.quizpracticing.model.Role;
import swp391.quizpracticing.repository.IRoleRepository;
import swp391.quizpracticing.service.IRoleService;

/**
 *
 * @author Mosena
 */
@Service
public class RoleService implements IRoleService {
    
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    private RoleDTO convertEntityToDTO(Role entity){
        return modelMapper.map(entity,RoleDTO.class);
    }

    @Override
    public List<RoleDTO> findRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO findRole(Integer id) {
        return convertEntityToDTO(roleRepository.getReferenceById(id));
    }
}
