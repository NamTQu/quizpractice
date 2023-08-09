/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import java.sql.Timestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.model.UserSubject;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.repository.IUserSubjectRepository;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserSubjectService;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import swp391.quizpracticing.dto.UserSubjectDTO;

/**
 *
 * @author Mosena
 */
@Service
@Transactional
public class UserSubjectService implements IUserSubjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserSubjectRepository userSubjectRepository;

    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private ISubjectService iSubjectService;
    @Override
    public List<UserSubject> getAllByUserId(Integer id) {
        return userSubjectRepository.getAllByUserIdAndRegistrationStatus(id, 1);
    }

    @Override
    public List<UserSubject> listAll() {

        return (List<UserSubject>) userSubjectRepository.findAll();
    }
    @Override
    public void save(UserSubject userSubject) {

        userSubjectRepository.save(userSubject);
    }

    @Override
    public UserSubject getId(UserSubject uk) {
        return null;
    }

    @Override
    public List<Subject> courseById(Integer id) {
        List<UserSubject> userCourses = listAll();
        List<UserSubject> filteredUserCourses = new ArrayList<>(); // create a new list

        for (UserSubject uc : userCourses) {
            if (uc.getUser().getId().equals(id)) {
                filteredUserCourses.add(uc); // add to the new list instead of original list
            }
        }

        List<Subject> subjects = new ArrayList<>();
        System.out.println(filteredUserCourses.size());
        for (UserSubject userCourse : filteredUserCourses) { // iterate over the filtered list
            Subject sub = subjectRepository.getById(userCourse.getSubject().getId());
            subjects.add(sub);
        }

        return subjects;
    }

    //Nam's code using DTO instead of entity
    @Override
    public Page<UserSubjectDTO> listAll(int pageNo, int pageSize, String sortBy,
            String order, String searchCriteria,
            Timestamp validFrom, Timestamp validTo,String status) {
        Pageable page;
        if(order.equals("asc")){
            page=PageRequest.of(pageNo - 1, pageSize,
                    Sort.by(sortBy).ascending());
        }
        else{
            page=PageRequest.of(pageNo - 1, pageSize,
                    Sort.by(sortBy).descending());
        }
        Specification<UserSubject> specification=(root,query,criteriaBuilder)
                ->{
            List<Predicate> predicates=new ArrayList<>();
            if(searchCriteria!=null){
                String searchPattern="%"+searchCriteria+"%";
                if(searchCriteria.contains("@")){
                    predicates.add(criteriaBuilder.like(
                        root.get("user").get("email"),
                        searchPattern));
                }
                else{
                    predicates.add(criteriaBuilder.like(
                        root.get("subject").get("title"),
                        searchPattern));
                }
            }
            if(validFrom!=null){
                predicates.add(criteriaBuilder.equal(
                        root.get("validFrom"),
                        validFrom));
            }
            if(validTo!=null){
                predicates.add(criteriaBuilder.equal(
                        root.get("validTo"),
                        validTo));
            }
            if(status!=null){
                predicates.add(criteriaBuilder.equal(
                        root.get("registrationStatus")
                                        .get("name"),
                        status));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
        Page<UserSubject> pageList=userSubjectRepository.findAll(specification,page);
        List<UserSubjectDTO> list=pageList
                .stream()
                .map(this::convertEntityToDTO).toList();
        return new PageImpl<>(list,page,
                pageList.getTotalElements());
    }

    @Override
    public UserSubjectDTO saveRegistration(UserSubjectDTO registrationDTO) {
        UserSubject registration=convertDTOToEntity(registrationDTO);
        return convertEntityToDTO(userSubjectRepository
                .save(registration));
    }

    @Override
    public UserSubjectDTO addRegistration(UserSubjectDTO registrationDTO) {
        UserSubject registration=convertDTOToEntity(registrationDTO);
        return convertEntityToDTO(userSubjectRepository
                .save(registration));
    }

    @Override
    public List<String> getRegistrationStatusList(Page<UserSubjectDTO> page) {
        List<String> list=new ArrayList<>();
        for(UserSubjectDTO item:page){
            String status=item.getRegistrationStatus().getName();
            if(!list.contains(status)){
                list.add(status);
            }
        }
        return list;
    }

    @Override
    public List<Timestamp> getValidFromList(Page<UserSubjectDTO> page) {
        List<Timestamp> list=new ArrayList<>();
        for(UserSubjectDTO item:page){
            Timestamp validFrom=item.getValidFrom();
            if(!list.contains(validFrom)){
                list.add(validFrom);
            }
        }
        return list;
    }

    @Override
    public List<Timestamp> getValidToList(Page<UserSubjectDTO> page) {
        List<Timestamp> list=new ArrayList<>();
        for(UserSubjectDTO item:page){
            Timestamp validTo=item.getValidTo();
            if(!list.contains(validTo)){
                list.add(validTo);
            }
        }
        return list;
    }

    @Override
    public List<UserSubjectDTO> findAllByCategory(Integer id) {
        List<UserSubject>userSubjects= userSubjectRepository.findAllBySubjectIn
                (iSubjectService.findAllSubjectsByCategoryId(id));
        return userSubjects.stream()
                .map(this::convertEntityToDTO).toList();
    }

    @Override
    public List<UserSubjectDTO> findByRegitrationTime(Timestamp startTime, Timestamp endTime) {
        List<UserSubject> userSubjects=userSubjectRepository.findUserSubjectsByRegistrationTimeBetween(startTime, endTime);
        return userSubjects.stream()
                .map(this::convertEntityToDTO).toList();
    }

    @Override
    public List<UserSubjectDTO> findByCategoryAndTime(Integer categoryId, Timestamp startTime, Timestamp endTime) {
        List<UserSubjectDTO> userSubjectDTOS=findAllByCategory(categoryId);
        List<UserSubjectDTO> us= new ArrayList<>();
        for (UserSubjectDTO dto: userSubjectDTOS){
            if (dto.getRegistrationTime().compareTo(startTime)>0 &&
            dto.getRegistrationTime().compareTo(endTime)<0){
                us.add(dto);
            }
        }
        return us ;
    }

    @Override
    public UserSubjectDTO getRegistration(Integer id){
        return convertEntityToDTO(userSubjectRepository
                .getReferenceById(id));
    }

    private UserSubjectDTO convertEntityToDTO(UserSubject entity){
        return modelMapper.map(entity, UserSubjectDTO.class);
    }
    private UserSubject convertDTOToEntity(UserSubjectDTO dto){
        return modelMapper.map(dto, UserSubject.class);
    }

    @Override
    public void remove(UserSubjectDTO registration) {
        userSubjectRepository.delete(convertDTOToEntity(registration));
    }
}
