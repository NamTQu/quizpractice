/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.repository;

/**
 *
 * @author Lenovo
 */
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.User;
@Repository 
public interface RegisterRepository extends JpaRepository<User, Long> {
     
    @Query("SELECT u FROM User u WHERE u.token = ?1")
    public User findByToken(String code);
    
    @Override
    public List<User> findAll();
   
}
