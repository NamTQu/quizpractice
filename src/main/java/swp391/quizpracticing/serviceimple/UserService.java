/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.Role;
import swp391.quizpracticing.model.User;
import swp391.quizpracticing.repository.IRoleRepository;
import swp391.quizpracticing.repository.IUserRepository;
import swp391.quizpracticing.service.IUserService;

/**
 *
 * @author Mosena
 */
@Service
@Transactional
public class UserService implements IUserService {
    
    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IRoleRepository roleRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    private UserDTO convertEntityToDTO(User entity){
        return modelMapper.map(entity, UserDTO.class);
    }

    private User convertDTOToEntity(UserDTO entity){
        return modelMapper.map(entity, User.class);
    }

    @Override
    public Page<UserDTO> getUsers(int pageNo, int pageSize, 
            String searchValue, Boolean gender, Boolean status, Integer roleId,
            String sortBy, String order) {
        Pageable page;
        if(order.equals("asc")){
            page = PageRequest.of(pageNo - 1, pageSize, 
                    Sort.by(sortBy).ascending());
        }
        else{
            page = PageRequest.of(pageNo - 1, pageSize, 
                    Sort.by(sortBy).descending());
        }
        Specification<User> specification=(root, query, criteriaBuilder) -> {
            List<Predicate> predicates=new ArrayList<>();
            if(gender!=null){
                predicates.add(criteriaBuilder.equal(root
                        .get("gender"), gender));
            }
            if(status!=null){
                predicates.add(criteriaBuilder.equal(root
                        .get("enable"), status));
            }
            if (roleId != null) {
                predicates.add(criteriaBuilder.equal(root
                        .get("role").get("id"), roleId));
            }
            if(searchValue!=null){
                String searchPattern = "%" + searchValue + "%";
                String searchBy;
                if(searchValue.contains("@")){
                    searchBy="email";
                }
                else if(searchValue.matches("\\d+")){
                    searchBy="mobile";
                }
                else{
                    searchBy="fullName";
                }
                predicates.add(criteriaBuilder.like(root
                        .get(searchBy), searchPattern));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
        Page<User> pageList=userRepository.findAll(specification,page);
        List<UserDTO> list = pageList
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(list, page,
                pageList.getTotalElements());
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public int countUsersByRolesLike(String role) {
        return userRepository.countUsersByRolesLike(role);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = convertEntityToDTO(userRepository
                .findByEmail(username));
        if (user == null) {
            throw new UsernameNotFoundException("Email does not exist in system. Please re-enter another email!");
        }
        return user;
    }

    @Override
    public void updateUser(Integer userId, Integer roleId, Boolean status) {
        Role r=roleRepository.getReferenceById(roleId);
        userRepository.updateUser(userId, r,status);
    }

    @Override
    public UserDTO findUser(Integer id) {
        return convertEntityToDTO(userRepository.getById(id));
    }

    @Override
    public UserDTO findUserByToken(String token) {
        User u=userRepository.getByToken(token);
        return u!=null?convertEntityToDTO(u):null;
    }

    @Override
    public void updateUserStatusAndToken(Integer userId, Boolean status) {
        userRepository.updateUserStatus(userId, status);
    }

    @Override
    public void remove(UserDTO u) {
        userRepository.delete(convertDTOToEntity(u));
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        if(user==null){
            return null;
        }
        return convertEntityToDTO(user);
    }

    @Override
    public List<User> getAllExpert() {
        return userRepository.findAllExpert();
    }

    @Override
    public User getByUserId(int id) {
        return userRepository.findById(id);
    }

}
