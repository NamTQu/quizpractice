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
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.SettingsDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.IBlogcategoryRepository;
import swp391.quizpracticing.repository.ICategoryRepository;
import swp391.quizpracticing.repository.IDimensionRepository;
import swp391.quizpracticing.repository.ILessontypeRepository;
import swp391.quizpracticing.repository.ILevelRepository;
import swp391.quizpracticing.repository.IRoleRepository;
import swp391.quizpracticing.repository.ISettingsRepository;
import swp391.quizpracticing.repository.ISubcategoryRepository;
import swp391.quizpracticing.repository.ISystemsettingsRepository;
import swp391.quizpracticing.repository.ITesttypeRepository;
import swp391.quizpracticing.service.ISettingsService;

/**
 *
 * @author Mosena
 */
@Service
@Transactional
public class SettingsService implements ISettingsService {
    
    @Autowired
    private ISettingsRepository settingsRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private ISystemsettingsRepository systemsettingsRepository;
    
    @Autowired
    private IBlogcategoryRepository blogCategoryRepository;
    
    @Autowired
    private IRoleRepository roleRepository;
    
    @Autowired
    private ITesttypeRepository testTypeRepository;
    
    @Autowired
    private ILevelRepository levelRepository;
    
    @Autowired
    private ILessontypeRepository lessonTypeRepository;
    
    @Autowired
    private ICategoryRepository categoryRepository;
    
    @Autowired
    private ISubcategoryRepository subCategoryRepository;
    
    @Autowired
    private IDimensionRepository dimensionRepository;
    

    
    private SettingsDTO convertEntityToDTO(Settings entity){
        return modelMapper.map(entity,SettingsDTO.class);
    }

    
    @Override
    public Page<SettingsDTO> getSettings(int pageNo, int pageSize, 
            String type, Boolean status, String sortBy,String order, 
            String value) {
        Pageable page;
        if(order.equals("asc")){
            page = PageRequest.of(pageNo - 1, pageSize, 
                    Sort.by(sortBy).ascending());
        }
        else{
            page = PageRequest.of(pageNo - 1, pageSize, 
                    Sort.by(sortBy).descending());
        }
        Specification<Settings> specification=(root,query,criteriaBuilder)->{
            List<Predicate>predicates=new ArrayList<>();
            if(type!=null){
                predicates.add(criteriaBuilder
                        .equal(root.get("type"), type));
            }
            if(status!=null){
                predicates.add(criteriaBuilder
                        .equal(root.get("status"),status));
            }
            if(value!=null){
                String searchPattern="%"+value+"%";
                predicates.add(criteriaBuilder.like(root.get("value")
                        ,searchPattern));
            }
            return criteriaBuilder.and(predicates
                    .toArray(Predicate[]::new));
        };
        Page<Settings>pageList=settingsRepository.findAll(specification, 
                page);
        List<SettingsDTO>list=pageList
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(list,page,
                pageList.getTotalElements());
    }
    
    @Override
    public void addSetting(String type, Integer order, String value, 
                                        String description) {
        Settings s=new Settings();
        s.setType(type);
        s.setOrder(order);
        s.setDescription(description);
        switch(type){
            case "User Roles" ->{
                Role r=addRole(value);
                s.setRole(r);
                s.setValue(r.getName());
                s.setStatus(r.getStatus());
                break;
            }
            case "System Settings" ->{
                Systemsettings ss=addSystemsetting(value);
                s.setSystemSetting(ss);
                s.setValue(ss.getName());
                s.setStatus(ss.getStatus());
                break;
            }
            case "Post Categories" ->{
                Blogcategory bc=addBlogCategory(value);
                s.setBlogcategory(bc);
                s.setValue(bc.getName());
                s.setStatus(bc.getStatus());
                break;
            }
            case "Subject Categories" ->{
                Category category = addSubjectCategory(value);
                s.setCategory(category);
                s.setValue(category.getName());
                s.setStatus(category.getStatus());
                break;
            }
            case "Test Types" ->{
                Testtype tt=addTestType(value);
                s.setTestType(tt);
                s.setValue(tt.getName());
                s.setStatus(tt.getStatus());
                break;
            }
            case "Question Levels" ->{
                Level level=addLevel(value);
                s.setLevel(level);
                s.setValue(level.getName());
                s.setStatus(level.getStatus());
                break;
            }
            case "Lesson Types" ->{
                Lessontype lt=addLessonType(value);
                s.setLessonType(lt);
                s.setValue(lt.getName());
                s.setStatus(lt.getStatus());
                break;
            }
            case "Subject Dimension" ->{
                Dimension d=addDimension(value, type, description);
                s.setDimension(d);
                s.setValue(d.getType());
                s.setStatus(d.getStatus());
                break;
            }
        }
        settingsRepository.save(s);
    }
    
    
    
    public Role addRole(String name){
        Role r=new Role();
        r.setName(name);
        r.setStatus(true);
        return roleRepository.save(r);
    }
    
    public Systemsettings addSystemsetting(String name){
        Systemsettings ss=new Systemsettings();
        ss.setName(name);
        ss.setStatus(true);
        return systemsettingsRepository.save(ss);
    }
    
    public Blogcategory addBlogCategory(String name){
        Blogcategory bc=new Blogcategory();
        bc.setName(name);
        bc.setStatus(true);
        return blogCategoryRepository.save(bc);
    }
    
    public Category addSubjectCategory(String name){
        Category c=new Category();
        c.setName(name);
        c.setStatus(true);
        return categoryRepository.save(c);
    }
    
    public Subcategory addSubjectSubcategory(String name, Category c){
        Subcategory sc=new Subcategory();
        sc.setName(name);
        sc.setCategory(c);
        sc.setStatus(true);
        return subCategoryRepository.save(sc);
    }
    
    public Testtype addTestType(String name){
        Testtype tt=new Testtype();
        tt.setName(name);
        tt.setStatus(true);
        return testTypeRepository.save(tt);
    }
    
    public Level addLevel(String name){
        Level level=new Level();
        level.setName(name);
        level.setStatus(true);
        return levelRepository.save(level);
    }
    
    public Lessontype addLessonType(String name){
        Lessontype lt=new Lessontype();
        lt.setName(name);
        lt.setStatus(true);
        return lessonTypeRepository.save(lt);
    }
    
    public Dimension addDimension(String name, String type, String description){
        Dimension dimension=new Dimension();
        dimension.setName(name);
        dimension.setType(type);
        dimension.setDescription(description);
        dimension.setStatus(true);
        return dimensionRepository.save(dimension);
    }

    @Override
    public List<String> findTypes() {
        return settingsRepository.findAllType();
    }

    @Override
    public SettingsDTO findById(Integer id) {
        return convertEntityToDTO(settingsRepository.getReferenceById(id));
    }

    @Override
    public void updateSettings(Integer id, String value, Integer order,
            Boolean status, String description) {
        settingsRepository.settingsUpdate(id, value, 
                order, status, description);
    }
}
