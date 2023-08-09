/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.dto.SubjectDTO;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Subject;
import swp391.quizpracticing.repository.ISubcategoryRepository;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.service.ISubjectService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;


/**
 *
 * @author Mosena
 */
@Service
public class SubjectService implements ISubjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ISubjectRepository subjectRepository;
    @Autowired
    private ISubcategoryRepository iSubcategoryRepository;
    private SubjectDTO convertEntityToDTO(Subject entity){
        return modelMapper.map(entity,SubjectDTO.class);
    }



    @Override
    public List<Subject> findByFeaturing(Boolean isFeatured) {
        return subjectRepository.findAllByFeatured(isFeatured);
    }

    @Override
    public List<Subject> listAll() {
        return subjectRepository.findAll();
    }
    @Override
    public Subject findByLesson(Integer id) {
        return subjectRepository.findByLessons_Id(id);
    }
    @Override
    public Page<Subject> getAllSubjectsPaginated(int pageNum, int itemPerPage) {
        return subjectRepository.findAllSubjectsPaginated(PageRequest.of(pageNum, itemPerPage));
    }


    @Override
    public List<Subject> searchByCourseName(String s) {
        return subjectRepository.findByBriefInfoContaining(s);
    }



    @Override
    public Subject getById(int id) {
        return subjectRepository.findById(id);
    }

    @Override
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Subject> findByExpertId(Integer id) {
        return subjectRepository.findByExpertId(id);
    }

    @Override
    public List<Subject> findSubjectsWithSorting(String field) {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Subject> findSubjectsWithPagination(int pageNum, int itemPerPage) {
        return subjectRepository.findAll(PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertId(Integer id, int pageNum, int itemPerPage) {
        Pageable pageRequest = PageRequest.of(pageNum, itemPerPage);
        return subjectRepository.findByOwnerId(id, pageRequest);
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByName(Integer id, String searchTerm, int pageNum, int itemPerPage) {
        return subjectRepository.findByOwnerIdAndName(id, searchTerm, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationByExpertIdAndByStatus(Integer id, Integer status, int pageNum, int itemPerPage) {
        return subjectRepository.findByOwnerIdAndStatus(id, status, PageRequest.of(pageNum,itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsWithPaginationAndSorting(int pageNum, int itemPerPage, String field) {
        return subjectRepository.findAll(PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, field)));
    }

    @Override
    public Page<Subject> searchForSubjectsByName(int pageNum, int itemPerPage, String searchTerm) {
        return subjectRepository.findByBriefInfoContainingIgnoreCase(searchTerm, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Page<Subject> findSubjectsByStatus(Boolean status, int pageNum, int itemPerPage) {
        return subjectRepository.findByStatus(status, PageRequest.of(pageNum, itemPerPage));
    }

    @Override
    public Boolean checkIfSubjectExistByBriefInfo(String briefInfo) {
        return subjectRepository.existsSubjectByBriefInfo(briefInfo);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        return null;
    }

    @Override
    public List<SubjectDTO> getAllSubject() {
        List<Subject> subject = subjectRepository.findAll();
        return subject
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).get();

    }

    @Override
    public SubjectDTO getDTOById(Integer id) {
        return convertEntityToDTO(subjectRepository.getReferenceById(id));
    }

    @Override
    public Page<SubjectDTO> findAll(int pageNo, int pageSize, String search,
                                    String order, List<Integer> categories) {
        Pageable page;
        if("asc".equals(order)){
            page=PageRequest.of(pageNo-1, pageSize,
                    Sort.by("lastUpdatedTime").ascending());
        }
        else{
            page=PageRequest.of(pageNo-1, pageSize,
                    Sort.by("lastUpdatedTime").descending());
        }
        Specification<Subject> specification= (root,query,criteriaBuilder)
                ->{
            List<Predicate> predicates=new ArrayList<>();
            if(search!=null){
                String searchPattern="%"+search+"%";
                predicates.add(criteriaBuilder
                        .like(root.get("title"),searchPattern));
            }
            if (categories!=null) {
                Join subjectJoin=root
                        .join("subCategories",JoinType.INNER);
                for (Integer category : categories) {
                    predicates.add(criteriaBuilder
                            .equal(subjectJoin.get("id"),
                                    category));
                }
            }
            return criteriaBuilder.and(predicates
                    .stream()
                    .toArray(Predicate[]::new));
        };
        Page<Subject> pageList=subjectRepository
                .findAll(specification, page);
        List<SubjectDTO> list=pageList.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(list,page,
                pageList.getTotalElements());
    }

    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    public Subject get(Integer id) throws Exception {
        Optional<Subject> result = subjectRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new Exception("Could not find any dimensions with ID " + id);
    }

    public void delete(Integer id) throws Exception {
        Long count = subjectRepository.countById(id);
        if (count == null || count == 0) {
            throw new Exception("Could not find any dimensions with ID " + id);
        }
        subjectRepository.deleteById(id);
    }
    @Override
    public List<Subject> findAllSubjectsByCategoryId(Integer id) {
        List<Subcategory>subcategories= iSubcategoryRepository.findSubcategoriesByCategory_Id(id);
        List<Subject> subjects = new ArrayList<>();
        for (Subcategory subcategory:subcategories){
            subjects.addAll(subcategory.getSubjects());
        }
        return subjects;
    }


    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

}