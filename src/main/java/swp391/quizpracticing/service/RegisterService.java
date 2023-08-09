/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.service;


import java.util.List;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.User;

/**
 *
 * @author Lenovo
 */
public interface RegisterService {

    public void register(UserDTO user);
    boolean verify(String verificationCode);

    public List<User> getAllAccount();


    


}
