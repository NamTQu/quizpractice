/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

/**
 *
 * @author Mosena
 */
public interface IVerificationService {
    public void sendVerification(String name, String email, String token
            ,String password);
    public void sendVerificationRegistration(Integer registrationId, String name, String email, String token
            ,String password);
}
