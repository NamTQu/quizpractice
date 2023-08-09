/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.User;

/**
 *
 * @author Admin
 */
@Repository
public interface IAccountRepository extends JpaRepository<User, Integer>{
    
    User findByEmail(String email);

    User findAccountByToken(String token);

    @Query(value = "SELECT email FROM iquiz.user where email = :email", nativeQuery = true)
    public String checkEmailExist(String email);
    
}
