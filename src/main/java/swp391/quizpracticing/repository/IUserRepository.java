/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.repository;

import jakarta.transaction.Transactional;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import swp391.quizpracticing.model.Role;
import swp391.quizpracticing.model.User;


/**
 *
 * @author Mosena
 */
@Repository
public interface IUserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Override
    public Page<User> findAll(Specification spec,Pageable pageable);

    @Override
    public Page<User> findAll(Pageable pageable);

    @Override
    public User save(User u);

    @Override
    public User getById(Integer id);

    public User findById(int id);

    @Query(value = "Select * from User where token = ?1",nativeQuery = true)
    public User getByToken(String token);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u SET u.role = :role, u.enable=:status WHERE u.id = :userId")
    public void updateUser(@Param("userId") Integer userId,
            @Param("role") Role role, @Param("status") Boolean status);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u SET u.enable=:status, u.token=null WHERE u.id = :userId")
    public void updateUserStatus(@Param("userId") Integer userId,
            @Param("status") Boolean status);
    
    @Modifying
    @Query("UPDATE User u SET u.role = :role WHERE u.id = :userId")
    public void updateUserRole(@Param("userId") Integer userId,
            @Param("role") Role role);
    
    @Query("select u from User u where u.email = :email")
    public User findByEmail(@Param("email")String email);
    @Query(value = "select count(*) from user_roles where role_id in (select id from roles where `name` = ?1)", nativeQuery = true)
    int countUsersByRolesLike(String role);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("Update User u set u.password = :password where u.id = :userId")
    public void updateUserPassword(@Param("userId") Integer id,@Param("password") String password);
    
    @Query(value="select * from User where full_name like %?1%",
            nativeQuery = true)
    public Page<User>searchByName(Pageable pageable,String fullName);

    @Query(value="select * from User where email like %?1%",
            nativeQuery = true)
    public Page<User>searchByEmail(Pageable pageable, String email);

    @Query(value="select * from User where mobile like %?1%",
            nativeQuery = true)
    public Page<User>searchByPhoneNumber(Pageable pageable, String phone);
    @Override
    public void delete(User u);

    @Query(value = "select * from iquiz.user where role_id = 2", nativeQuery = true)
    public List<User> findAllExpert();

}
