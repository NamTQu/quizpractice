/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.Role;
import swp391.quizpracticing.model.User;
import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.UserDTO;

/**
 *
 * @author Mosena
 */
public interface IUserService extends UserDetailsService {
    User findByEmail(String email);
    int countUsersByRolesLike(String role);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    public Page<UserDTO> getUsers(int pageNo, int pageSize,
                                 String searchValue, Boolean gender, Boolean status, Integer roleId,
                                 String sortBy, String order);


    public void updateUser(Integer userId,Integer roleId, Boolean status);
    public UserDTO findUser(Integer id);
    public UserDTO findUserByToken(String token);
    public void updateUserStatusAndToken(Integer userId, Boolean status);
    public void remove(UserDTO u);

    public List<User> getAllExpert();

    public User getByUserId(int id);
    public UserDTO findUserByEmail(String email);
}
