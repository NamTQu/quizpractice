/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Role;

/**
 *
 * @author Mosena
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    @Override
    public Role getReferenceById(Integer id);
    
    @Override
    public List<Role> findAll();
    
    @Override
    public Role save(Role r);
}
