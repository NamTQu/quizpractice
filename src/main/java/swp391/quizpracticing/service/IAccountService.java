/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.User;

/**
 *
 * @author Admin
 */
@Service
public interface IAccountService {
    
    void updateResetPasswordToken(String token, String email);

    User getByResetPasswordToken(String token);

    void updatePassword(User acc, String password);

    public void updateUserProfile(UserDTO userSession);
    
}
