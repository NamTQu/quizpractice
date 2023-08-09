/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import java.util.List;
import swp391.quizpracticing.dto.RoleDTO;

/**
 *
 * @author Mosena
 */
public interface IRoleService {
    public List<RoleDTO> findRoles();
    public RoleDTO findRole(Integer id);
}
