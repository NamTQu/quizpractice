/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import swp391.quizpracticing.dto.*;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.ISubjectRepository;
import swp391.quizpracticing.service.*;
import swp391.quizpracticing.serviceimple.DimensionService;
import swp391.quizpracticing.serviceimple.PricepackageService;
import swp391.quizpracticing.serviceimple.SubjectService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Mosena
 */
@Controller
public class CourseContentController {

    @Autowired
    private ISubjectService iSubjectService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ISubcategoryService iSubcategoryService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private IDimensionService iDimensionService;

    @Autowired
    private DimensionService dimensionService;

    @Autowired
    private PricepackageService pricepackageService;

    @Autowired
    private IPricepackageService iPricepackageService;


    private final String FOLDER_PATH = "C:\\Users\\DELL\\Documents\\2_CodingZone\\2_InSchool_(FPTUni)\\5_SWP391\\SWP391GitProject\\summer2023-swp391.se1714-g5\\src\\main\\resources\\static\\database_images";

    private final String FOLDER_PATH_2 = "src/main/resources/static/database_images";


    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("admin/subjects-list")
    public String AdminGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") String pageNumString,
                                         @RequestParam(name = "itemPerPage", defaultValue = "10") String itemPerPageString,
                                         @RequestParam(name = "subject-name", defaultValue = "") String searchTermNoTrim,
                                         @RequestParam(name = "categoriesId", required = false) String[] selectedCategoriesIdString,
                                         @RequestParam(name = "status", defaultValue = "-1") String status,
                                         @RequestParam(name = "check", defaultValue = "false") Boolean check,
//                                         @ModelAttribute(name = "newSubject") Subject newSubject,
                                         Model model, HttpSession session) {

        System.out.println("check: " + check);
        if(check != null) {
            model.addAttribute("check", check);
        }

        //Check if the pageNum and itemPerPage is number
        try {
            int pageNum = Integer.parseInt(pageNumString);
            int itemPerPage = Integer.parseInt(itemPerPageString);
        } catch (NumberFormatException e) {
            model.addAttribute("backUrl", "admin/subjects-list");
            return "components/not-found-page.html";
        }

        //Check if selected_categories_id is number and Check if selected_categories_id is in the allowed range
        List<Category> allCategories = iCategoryService.listAll();

        ArrayList<Integer> selectedCategoriesIdList = new ArrayList<>();
        if(selectedCategoriesIdString != null && selectedCategoriesIdString.length != 0) {
            try {
                for(String i : selectedCategoriesIdString) {
                    Integer categoryId = Integer.parseInt(i);
                    selectedCategoriesIdList.add(categoryId);
                    if(categoryId > allCategories.size()) {
                        return "components/not-found-page.html";
                    }
                }
            } catch (NumberFormatException e) {
                model.addAttribute("backUrl", "admin/subjects-list");
                return "components/not-found-page.html";
            }
        }

        Integer[] selectedCategoriesId = new Integer[selectedCategoriesIdList.size()];
        selectedCategoriesId = selectedCategoriesIdList.toArray(selectedCategoriesId);

        //Check if status is in the range of values or not
        if(!(status.equals("-1") || status.equals("true") || status.equals("false"))) {
            model.addAttribute("backUrl", "admin/subjects-list");
            return "components/not-found-page.html";
        }

        Integer pageNum = Integer.parseInt(pageNumString);
        Integer itemPerPage = Integer.parseInt(itemPerPageString);

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject newSubject = (Subject) session.getAttribute("newSubject");
        if(newSubject != null) {
            model.addAttribute("newSubject", newSubject);
        }

        String searchTerm = searchTermNoTrim.trim();

        //Get all categories and subcategories and status
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        allCategories = iCategoryService.listAll();

        List<Subject> allSubjects = iSubjectService.listAll();

        Set<Boolean> allStatus = new HashSet<>();
        for(Subject subject : allSubjects) {
            allStatus.add(subject.getStatus());
        }

        if(searchTerm.isEmpty() && (selectedCategoriesId == null || selectedCategoriesId.length == 0) && status.equals("-1")) {  // display all subjects (no search, no filter)
            Page<Subject> subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

            if(!subjectsWithPagination.hasContent()) {
                subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPagination);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            Page<Subject> subjectsWithPagination = iSubjectService.searchForSubjectsByName(pageNum, itemPerPage, searchTerm);

            model.addAttribute("subjects", subjectsWithPagination);
        } else {  //Perform filtering
            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && (selectedCategoriesId == null || selectedCategoriesId.length == 0)) {  //filter by status
                subjectsWithPagination = iSubjectService.findSubjectsByStatus(Boolean.valueOf(status), pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by catecories_id
                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    subjectsWithPagination = iSubjectService.findSubjectsWithPagination(pageNum, itemPerPage);

                    if(!subjectsWithPagination.hasContent()) {
                        subjectsWithPagination = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
                    }

                    model.addAttribute("subjects", subjectsWithPagination);
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    List<Subject> filteredSubjects = new ArrayList<>();

                    for(Integer i : subjectsId) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                    filteredSubjects.sort(Comparator.comparing(Subject::getId));

                    int totalSubjects = filteredSubjects.size();

                    List<Subject> filteredSubjectsPagination = new ArrayList<>();
                    int startItem = pageNum*itemPerPage;
                    int toIndex = Math.min(startItem + itemPerPage, filteredSubjects.size());
                    filteredSubjectsPagination = filteredSubjects.subList(startItem, toIndex);

                    subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, "id")), totalSubjects);

                    model.addAttribute("subjects", subjectsWithPagination);
                }

            }
            if (!status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by both

                List<Subject> filteredSubjects = new ArrayList<>();

                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    filteredSubjects = iSubjectService.listAll();
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    for(Integer i : subjectsId) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                }

                //status
                List<Subject> filteredSubjectsFull = new ArrayList<>();
                for(Subject subject : filteredSubjects) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjectsFull.add(subject);
                    }
                }
                filteredSubjectsFull.sort(Comparator.comparing(Subject::getId));

                int totalSubjects = filteredSubjectsFull.size();
//                subjectsWithPagination = new PageImpl<>(filteredSubjectsFull, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                List<Subject> filteredSubjectsPagination = new ArrayList<>();
                int startItem = pageNum*itemPerPage;
                int toIndex = Math.min(startItem + itemPerPage, filteredSubjectsFull.size());
                filteredSubjectsPagination = filteredSubjectsFull.subList(startItem, toIndex);

                subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);

                model.addAttribute("subjects", subjectsWithPagination);
            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        if(selectedCategoriesId != null && selectedCategoriesId.length != 0) {
            System.out.println("selectedCategoriesId size = " + selectedCategoriesId.length);
            StringBuilder pagination_links = new StringBuilder(selectedCategoriesId[0].toString());
            for(int i = 1; i < selectedCategoriesId.length; i++) {
                pagination_links.append(",").append(selectedCategoriesId[i].toString());
            }

            List<Category> selectedCategories = new ArrayList<>();
            for(Integer id : selectedCategoriesId) {
                Category c = iCategoryService.getById(id);
                selectedCategories.add(c);
            }

            System.out.println(pagination_links.toString());

            String[] strArr = pagination_links.toString().split(",");
            Integer[] intArr = new Integer[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }

            model.addAttribute("selectedCategories", selectedCategories);
            model.addAttribute("used_for_pagination_links", pagination_links.toString());
            model.addAttribute("string_to_int_array", intArr);
            model.addAttribute("selectedCategoriesId", Arrays.asList(selectedCategoriesId));
        }


        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";
    }

    @GetMapping("expert/subjects-list")
    public String ExpertGetToSubjectsList(@RequestParam(name = "pageNum", defaultValue = "0") String pageNumString,
                                          @RequestParam(name = "itemPerPage", defaultValue = "10") String itemPerPageString,
                                          @RequestParam(name = "subject-name", defaultValue = "") String searchTerm,
                                          @RequestParam(name = "categoriesId", required = false) String[] selectedCategoriesIdString,
                                          @RequestParam(name = "status", defaultValue = "-1") String status,
                                          @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                          Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        //Check if the pageNum and itemPerPage is number
        try {
            int pageNum = Integer.parseInt(pageNumString);
            int itemPerPage = Integer.parseInt(itemPerPageString);
        } catch (NumberFormatException e) {
            model.addAttribute("backUrl", "expert/subjects-list");
            return "components/not-found-page.html";
        }

        //Check if selected_categories_id is number and Check if selected_categories_id is in the allowed range
//        List<Category> allCategories = iCategoryService.listAll();

        ArrayList<Integer> selectedCategoriesIdList = new ArrayList<>();
        if(selectedCategoriesIdString != null && selectedCategoriesIdString.length != 0) {
            try {
                for(String i : selectedCategoriesIdString) {
                    Integer categoryId = Integer.parseInt(i);
                    selectedCategoriesIdList.add(categoryId);
//                    if(categoryId > allCategories.size()) {
//                        return "components/not-found-page.html";
//                    }
                }
            } catch (NumberFormatException e) {
                model.addAttribute("backUrl", "admin/subjects-list");
                return "components/not-found-page.html";
            }
        }

        Integer[] selectedCategoriesId = new Integer[selectedCategoriesIdList.size()];
        selectedCategoriesId = selectedCategoriesIdList.toArray(selectedCategoriesId);

        //Check if status is in the range of values or not
        if(!(status.equals("-1") || status.equals("true") || status.equals("false"))) {
            model.addAttribute("backUrl", "expert/subjects-list");
            return "components/not-found-page.html";
        }

        Integer pageNum = Integer.parseInt(pageNumString);
        Integer itemPerPage = Integer.parseInt(itemPerPageString);

        //Find subjects by expert_id
        Page<Subject> subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);
        System.out.println("size: " + subjectsWithPaginationByExpertId.getSize());
        List<Subject> subjectsByExpertId = iSubjectService.findByExpertId(loggedinUser.getId());
        System.out.println("list size: " + subjectsByExpertId.size());

        Set<Boolean> allStatus = new HashSet<>();
        for(Subject subject : subjectsWithPaginationByExpertId) {
            allStatus.add(subject.getStatus());
        }

        Set<Integer> allCategoriesId = new HashSet<>();
        for(Subject subject : subjectsWithPaginationByExpertId) {
            List<Subcategory> subcategories = subject.getSubCategories();
            for(Subcategory subcategory : subcategories) {
                int cId = subcategory.getCategory().getId();
                allCategoriesId.add(cId);
            }
        }

        Set<Category> allCategories = new HashSet<>();
        for(Integer id : allCategoriesId) {
            Category c = iCategoryService.getById(id);
            allCategories.add(c);
        }



        if(searchTerm.isEmpty() && (selectedCategoriesId == null || selectedCategoriesId.length == 0) && status.equals("-1")) {  // display all subjects (no search, no filter)

            if(!subjectsWithPaginationByExpertId.hasContent()) {
//                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPagination(0, itemPerPage);
                subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), 0, itemPerPage);
            }

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);

        } else if(!searchTerm.isEmpty()) {  // perform searching
            subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertIdAndByName(loggedinUser.getId(), searchTerm, pageNum, itemPerPage);
            System.out.println("number of subjects = " + subjectsWithPaginationByExpertId.getTotalElements());

            model.addAttribute("subjects", subjectsWithPaginationByExpertId);
        } else {  //Perform filtering

            Page<Subject> subjectsWithPagination;

            if(!status.equals("-1") && (selectedCategoriesId == null || selectedCategoriesId.length == 0)) {  //filter by status
                Integer statusInt = Boolean.parseBoolean(status) ? 1 : 0;
                subjectsWithPagination = iSubjectService.findSubjectsWithPaginationByExpertIdAndByStatus(loggedinUser.getId(), statusInt, pageNum, itemPerPage);
                model.addAttribute("subjects", subjectsWithPagination);
            }
            if(status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by catecory_id

                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), pageNum, itemPerPage);

                    if(!subjectsWithPaginationByExpertId.hasContent()) {
                        subjectsWithPaginationByExpertId = iSubjectService.findSubjectsWithPaginationByExpertId(loggedinUser.getId(), 0, itemPerPage);
                    }

                    model.addAttribute("subjects", subjectsWithPaginationByExpertId);
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list

                    //Get all selected categories
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    //From the list of selected categories, get all subcategories from that
                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    //from that list of selected subcategories, get all subjects + subjects_id from that
                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    Set<Integer> subjectsIdFiltered = new HashSet<>();

                    //check if the subjects-id that exist in the all subjects by expert
                    for(Subject subject : subjectsByExpertId) {
                        if(subjectsId.contains(subject.getId())) {
                            subjectsIdFiltered.add(subject.getId());
                        }
                    }

                    System.out.println("subjectsIdFiltered: " + subjectsIdFiltered.size());

                    //From the list of subjects-id, get the subjects
                    List<Subject> filteredSubjects = new ArrayList<>();

                    for(Integer i : subjectsIdFiltered) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                    filteredSubjects.sort(Comparator.comparing(Subject::getId));

                    int totalSubjects = filteredSubjects.size();

                    List<Subject> filteredSubjectsPagination = new ArrayList<>();
                    int startItem = pageNum*itemPerPage;
                    int toIndex = Math.min(startItem + itemPerPage, filteredSubjects.size());
                    filteredSubjectsPagination = filteredSubjects.subList(startItem, toIndex);

                    subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage).withSort(Sort.by(Sort.Direction.ASC, "id")), totalSubjects);

                    model.addAttribute("subjects", subjectsWithPagination);

                }

            }
            if (!status.equals("-1") && selectedCategoriesId != null && selectedCategoriesId.length != 0) {  //filter by both

                List<Subject> filteredSubjects = new ArrayList<>();

                //check categories-id
                if(Arrays.asList(selectedCategoriesId).contains(-1)) {
                    filteredSubjects = iSubjectService.findByExpertId(loggedinUser.getId());
                } else {
                    //Take all subjects + loop through all the subjects and get its list of subcategories
                    //loop through received sub-categories and get + get categories from the subcategories list
                    List<Category> selectedCategories = new ArrayList<>();
                    for(Integer id : selectedCategoriesId) {
                        Category c = iCategoryService.getById(id);
                        selectedCategories.add(c);
                    }

                    model.addAttribute("selectedCategories", selectedCategories);

                    List<Subcategory> subcategories = new ArrayList<>();
                    for(Category category : selectedCategories) {
                        List<Subcategory> sub = category.getSubCategories();
                        subcategories.addAll(sub);
                    }

                    Set<Integer> subjectsId = new HashSet<>();
                    List<Subject> subjectsRaw = new ArrayList<>();
                    //get subjects from selected categories
                    for(Subcategory sub : subcategories) {
                        List<Subject> s = sub.getSubjects();
                        subjectsRaw.addAll(s);
                    }
                    for(Subject s : subjectsRaw) {
                        subjectsId.add(s.getId());
                    }

                    Set<Integer> subjectsIdFiltered = new HashSet<>();

                    //check if the subjects-id that exist in the all subjects by expert
                    for(Subject subject : subjectsByExpertId) {
                        if(subjectsId.contains(subject.getId())) {
                            subjectsIdFiltered.add(subject.getId());
                        }
                    }

                    for(Integer i : subjectsIdFiltered) {
                        Subject s = iSubjectService.getById(i);
                        filteredSubjects.add(s);
                    }
                }

                //check status
                List<Subject> filteredSubjectsFull = new ArrayList<>();
                for(Subject subject : filteredSubjects) {
                    if(subject.getStatus() == Boolean.parseBoolean(status)) {
                        filteredSubjectsFull.add(subject);
                    }
                }
                filteredSubjectsFull.sort(Comparator.comparing(Subject::getId));

                int totalSubjects = filteredSubjectsFull.size();
//                subjectsWithPagination = new PageImpl<>(filteredSubjectsFull, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                List<Subject> filteredSubjectsPagination = new ArrayList<>();
                int startItem = pageNum*itemPerPage;
                int toIndex = Math.min(startItem + itemPerPage, filteredSubjectsFull.size());
                filteredSubjectsPagination = filteredSubjectsFull.subList(startItem, toIndex);

                subjectsWithPagination = new PageImpl<>(filteredSubjectsPagination, PageRequest.of(pageNum, itemPerPage), totalSubjects);

                model.addAttribute("subjects", subjectsWithPagination);

            }
        }

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("subjectName", searchTerm);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("itemPerPage", itemPerPage);
        model.addAttribute("selectedItemPerPage", itemPerPage);
        model.addAttribute("categories", allCategories);
        if(selectedCategoriesId != null && selectedCategoriesId.length != 0) {
            System.out.println("selectedCategoriesId size = " + selectedCategoriesId.length);
            StringBuilder pagination_links = new StringBuilder(selectedCategoriesId[0].toString());
            for(int i = 1; i < selectedCategoriesId.length; i++) {
                pagination_links.append(",").append(selectedCategoriesId[i].toString());
            }

            List<Category> selectedCategories = new ArrayList<>();
            for(Integer id : selectedCategoriesId) {
                Category c = iCategoryService.getById(id);
                selectedCategories.add(c);
            }

            System.out.println(pagination_links.toString());

            String[] strArr = pagination_links.toString().split(",");
            Integer[] intArr = new Integer[strArr.length];

            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }

            model.addAttribute("selectedCategories", selectedCategories);
            model.addAttribute("used_for_pagination_links", pagination_links.toString());
            model.addAttribute("string_to_int_array", intArr);
            model.addAttribute("selectedCategoriesId", Arrays.asList(selectedCategoriesId));
        }
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("selectedStatus", status);

        return "course_content/subjects-list";

    }

    @GetMapping("/admin/new-subject")
    public String getToNewSubjectForm(@ModelAttribute(name = "name") String subjectName,
                                      @ModelAttribute(name = "owner") User owner,
                                      @ModelAttribute(name = "selectedCategory") Category selectedCategory,
                                      @ModelAttribute(name = "selectedSubcategory") Subcategory selectedSubcategory,
                                      @ModelAttribute(name = "selectedFeatured") String selectedFeatured,
                                      @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                      @ModelAttribute(name = "description") String description,
                                      @ModelAttribute(name = "thumbnail") MultipartFile thumbnail,
                                      @ModelAttribute(name = "ms1") String ms1,
                                      @ModelAttribute(name = "ms2") String ms2,
                                      Model model, HttpSession session) {

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        List<Category> allCategories = iCategoryService.listAll();
        List<User> allExperts = iUserService.getAllExpert();
        List<Boolean> isFeatured = new ArrayList<>(Arrays.asList(true, false));
        List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));
        List<Subcategory> allSubcategories = iSubcategoryService.getAll();

        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("categories", allCategories);
        model.addAttribute("subcategories", allSubcategories);
        model.addAttribute("experts", allExperts);
        model.addAttribute("featured", isFeatured);
        model.addAttribute("status", status);

        if(ms1 != null || ms2 != null) {
            model.addAttribute("name", subjectName);
            model.addAttribute("selectedOwner", owner);
            model.addAttribute("selectedCategory", selectedCategory);
            model.addAttribute("selectedSubcategory", selectedSubcategory);
            model.addAttribute("selectedFeatured", selectedFeatured);
            model.addAttribute("selectedStatus", selectedStatus);
            model.addAttribute("description", description);
//            model.addAttribute("thumbnail", thumbnail);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("thumbnail");
            model.addAttribute("thumbnail", uploadedImg);
            model.addAttribute("ms1", ms1);
            model.addAttribute("ms2", ms2);
        }

        return "course_content/subject_form";
    }

    private SubcategoryDTO convertEntityToDTO(Subcategory entity){
        return modelMapper.map(entity,SubcategoryDTO.class);
    }

    @PostMapping("/admin/new-subject-submit")
    public String addNewSubject(@RequestParam(name = "name") String subjectNameNotTrim,
                                @RequestParam(name = "category") Integer categoryId,
                                @RequestParam(name = "subcategory") Integer subcategoryId,
                                @RequestParam(name = "owner") Integer ownerId,
                                @RequestParam(name = "featured") Boolean featured,
                                @RequestParam(name = "status") Boolean status,
                                @RequestParam(name = "description") String descriptionNotTrim,
                                @RequestParam(name = "thumbnail", required = false) MultipartFile multipartFile,
                                RedirectAttributes redirectAttribute,
                                Model model, HttpSession session) throws IOException {

        Boolean check = true;
        String ms1 = "", ms2 = "";

        String subjectName = subjectNameNotTrim.trim();
        System.out.println("subjectNameNotTrim: " + subjectNameNotTrim);
        System.out.println("subjectName: " + subjectName);

        String description = descriptionNotTrim.trim();

        //check if any input is empty, return false immediately
        if(subjectName.isEmpty() || description.isEmpty() || multipartFile.isEmpty()) {
            check = false;

            String emptyError = "Please fill all the input before submit!";

            redirectAttribute.addFlashAttribute("name", subjectName);
            redirectAttribute.addFlashAttribute("owner", iUserService.getByUserId(ownerId));
            redirectAttribute.addFlashAttribute("selectedCategory", iCategoryService.getById(categoryId));
            redirectAttribute.addFlashAttribute("selectedSubcategory", iSubcategoryService.getById(subcategoryId));
            redirectAttribute.addFlashAttribute("selectedFeatured", featured.toString());
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("description", description);
            redirectAttribute.addFlashAttribute("emptyError", emptyError);

            return "redirect:../admin/new-subject";
        }

        //check if subject name is existed
        if(iSubjectService.checkIfSubjectExistByBriefInfo(subjectName)) {
            System.out.println("check if subject exist: " + iSubjectService.checkIfSubjectExistByBriefInfo(subjectName));
            check = false;
            ms1 = "Subject Name Existed!";
            System.out.println("condition 1 fail");
        }

        //check if the selected subcategory match the selected category
        Subcategory selectedSubcategory = iSubcategoryService.getById(subcategoryId);
        Category selectedCategory = iCategoryService.getById(categoryId);
        List<Subcategory> selectedCategory_subcategories = selectedCategory.getSubCategories();
        for(Subcategory subcategory : selectedCategory_subcategories) {
            if (!subcategory.getCategory().getName().equals(selectedSubcategory.getCategory().getName())) {
                check = false;
                ms2 = "Selected Subcategory DOES NOT MATCH Selected Category!";
                System.out.println("condition 2 fail");
            }
        }


        System.out.println(check);

        if(check) {  //save new subject

            String fileName;
            String originalFileName;
            String fileExtension;

            if(multipartFile != null) {
                //Take the file name user has uploaded
                originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                System.out.println("originalFileName: " + originalFileName);
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                fileName = System.currentTimeMillis() + fileExtension;
                System.out.println("fileName after changing: " + fileName);


                //Get the Absolute Path of the file Name (include file name)
                Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
                System.out.println("fileNameAndPath: " + fileNameAndPath);
                //Store the actual file to the file directory
                Files.write(fileNameAndPath, multipartFile.getBytes());
            } else {
                fileName="null";
            }

            List<Subcategory> subcategories = new ArrayList<>();
            subcategories.add(iSubcategoryService.getById(subcategoryId));

            Subject newSubject = new Subject();
            newSubject.setBriefInfo(subjectName);
            newSubject.setDescription(description);
            newSubject.setFeatured(featured);
            newSubject.setStatus(status);
            newSubject.setOwner(iUserService.getByUserId(ownerId));
            newSubject.setSubCategories(subcategories);
            newSubject.setThumbnail(fileName);
            Date createdTime=Date.valueOf(LocalDate.now());
            newSubject.setCreatedTime(createdTime);
            Date lastUpdatedTime=Date.valueOf(LocalDate.now());;
            newSubject.setLastUpdatedTime(lastUpdatedTime);

            newSubject = iSubjectRepository.save(newSubject);

            model.addAttribute("check", check);
            redirectAttribute.addFlashAttribute("newSubject", newSubject);
            session.setAttribute("newSubject", newSubject);
            return "redirect:../admin/subjects-list?check=" + check;
        } else {  //errors

            System.out.println("ms1: " + ms1);
            System.out.println("ms2: " + ms2);

            redirectAttribute.addFlashAttribute("name", subjectName);
            redirectAttribute.addFlashAttribute("owner", iUserService.getByUserId(ownerId));
            redirectAttribute.addFlashAttribute("selectedCategory", iCategoryService.getById(categoryId));
            redirectAttribute.addFlashAttribute("selectedSubcategory", iSubcategoryService.getById(subcategoryId));
            redirectAttribute.addFlashAttribute("selectedFeatured", featured.toString());
            redirectAttribute.addFlashAttribute("selectedStatus", status.toString());
            redirectAttribute.addFlashAttribute("description", description);
            redirectAttribute.addFlashAttribute("thumbnail", multipartFile);
            session.setAttribute("thumbnail", multipartFile);
            redirectAttribute.addFlashAttribute("ms1", ms1);
            redirectAttribute.addFlashAttribute("ms2", ms2);
            return "redirect:../admin/new-subject";
        }

    }

    @GetMapping("admin/subject-detail")
    public String AdminGetToSubjectDetails(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                           @RequestParam(name = "id", required = true) Integer id,
                                           @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                           @RequestParam(name = "status", defaultValue = "-1") String status,
                                           @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                           Model model, HttpSession session) {

        System.out.println("check: " + check);
        if(check != null) {
            model.addAttribute("check", check);
        }

        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject subject = iSubjectService.getSubjectById(id);
        System.out.println("subject name: " + subject.getBriefInfo());
        System.out.println("subject id: " + subject.getId());
        System.out.println("subject thumbnail: " + subject.getThumbnail());
        model.addAttribute("subject", subject);
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("pageTitle", "Subject Detail (ID: " + id + ")");
        return "course_content/subjectdetails";

    }

    @GetMapping("/admin/subject-details-edit")
    public String showEditSubject(@RequestParam("id") Integer id,
                                  @ModelAttribute(name = "title") String title,
                                  @ModelAttribute(name = "briefInfo") String briefInfo,
                                  @ModelAttribute(name = "description") String description,
                                  @ModelAttribute(name = "selectedStatus") String selectedStatus,
                                  @ModelAttribute(name = "thumbnail") MultipartFile thumbnail,
                                  Model model, HttpSession session, RedirectAttributes ra) {
        try {
            Subject subject = subjectService.get(id);
            UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
            List<Boolean> status = new ArrayList<>(Arrays.asList(true, false));

            model.addAttribute("userSession", session.getAttribute("user"));
            model.addAttribute("status", status);
            model.addAttribute("subject", subject);
            model.addAttribute("pageTitle", "Edit Subject (ID: " + id + ")");
            model.addAttribute("title", title);
            model.addAttribute("briefInfo", briefInfo);
            model.addAttribute("description", description);
            model.addAttribute("selectedStatus", selectedStatus);

            MultipartFile uploadedImg = (MultipartFile) session.getAttribute("thumbnail");
            model.addAttribute("thumbnail", uploadedImg);
            return "course_content/edit_subjectdetails";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/subjects-list";
        }
    }

    @PostMapping("/subject/save")
    public String saveSubject(@RequestParam(name = "id", required = true) Integer id,
                              @RequestParam(name = "thumbnail", required = false) MultipartFile multipartFile,
                              RedirectAttributes redirectAttribute,
                              Model model, Subject subject, HttpSession session) throws IOException {

        String fileName;
        String originalFileName;
        String fileExtension;

        if(multipartFile != null) {
            //Take the file name user has uploaded
            originalFileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            System.out.println("originalFileName: " + originalFileName);
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            fileName = System.currentTimeMillis() + fileExtension;
            System.out.println("fileName after changing: " + fileName);

            //Get the Absolute Path of the file Name (include file name)
            Path fileNameAndPath = Paths.get(FOLDER_PATH_2, fileName);
            System.out.println("fileNameAndPath: " + fileNameAndPath);
            //Store the actual file to the file directory
            Files.write(fileNameAndPath, multipartFile.getBytes());
        } else {
            fileName="null";
        }

        Subject existingSubject = subjectService.getSubjectById(id);
        existingSubject.setTitle(subject.getTitle());
        existingSubject.setBriefInfo(subject.getBriefInfo());
        existingSubject.setDescription(subject.getDescription());
        existingSubject.setStatus(subject.getStatus());
        existingSubject.setThumbnail(fileName);

        subjectService.saveSubject(existingSubject);
        redirectAttribute.addFlashAttribute("existingSubject", existingSubject);
        session.setAttribute("existingSubject", existingSubject);
        redirectAttribute.addFlashAttribute("message", "The subject has been saved successfully.");
        return "redirect:../admin/subjects-list";
    }

    @GetMapping("/admin/subject-dimension")
    public String AdminGetToDimensionListPage(HttpSession session,Model model) {
        List<DimensionDTO> dimension = (List<DimensionDTO>) iDimensionService.getAllDimension();
        model.addAttribute("dimension", dimension);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "course_content/dimension";
    }

    @GetMapping("/admin/subject-dimension/new")
    public String showNewDimension(HttpSession session,Model model) {
        model.addAttribute("dimension", new Dimension());
        model.addAttribute("userSession", session.getAttribute("user"));
        return "course_content/add_dimension";
    }

    @PostMapping("/dimension/save")
    public String saveDimension(Dimension dimension, RedirectAttributes ra) {
        dimensionService.save(dimension);
        ra.addFlashAttribute("message", "The dimension has been saved successfully.");
        return "redirect:/admin/subject-dimension";
    }

    @GetMapping("/admin/subject-dimension/edit")
    public String showEditForm(@RequestParam("id") Integer id,HttpSession session, Model model, RedirectAttributes ra) {
        try {
            Dimension dimension = dimensionService.get(id);
            model.addAttribute("dimension", dimension);
            model.addAttribute("userSession", session.getAttribute("user"));
            model.addAttribute("pageTitle", "EDIT DIMENSION (ID: " + id + ")");
            return "course_content/edit_dimension";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/subject-dimension";
        }
    }

    @GetMapping("/admin/subject-dimension/delete")
    public String deleteDimension(@RequestParam("id") Integer id, RedirectAttributes ra) {
        try {
            dimensionService.delete(id);
            ra.addFlashAttribute("message", "The dimension ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/subject-dimension";
    }

    @GetMapping("/admin/subject-pricepackage")
    public String AdminGetToPricepackage(@RequestParam(name = "id", required = true) Integer id, @RequestParam(name = "newPricePackage", required = false) Pricepackage pricepackage, HttpSession session, Model model) {
        List<Pricepackage> pricepackages = iPricepackageService.getPricepackageBySubject(id);
        model.addAttribute("pricepackages", pricepackages);
        model.addAttribute("subjectId", id);
        model.addAttribute("userSession", session.getAttribute("user"));
        if(pricepackage != null) {
            model.addAttribute("newPricePackage", pricepackage);
        }
        return "course_content/pricepackage";
    }

    @GetMapping("/admin/subject-pricepackage/new")
    public String showNewPricepackage(@RequestParam(name = "subject-id") Integer subjectId,
                                      @RequestParam(name = "id", defaultValue = "-1") Integer id,
                                      @RequestParam(name = "name", defaultValue = "") String name,
                                      @RequestParam(name = "duration", defaultValue = "1") Integer duration,
                                      @RequestParam(name = "status", defaultValue = "true") String status,
                                      @RequestParam(name = "list-price", defaultValue = "0") Double listPrice,
                                      @RequestParam(name = "sale-price", defaultValue = "0") Double salePrice,
                                      @RequestParam(name = "description", defaultValue = "") String description,
                                      HttpSession session, Model model) {
        model.addAttribute("hiddenId", -1);
        model.addAttribute("pricepackage", new Pricepackage());
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("action", "add");
        model.addAttribute("subjectId", subjectId);
        return "price_package/price_package_form";
    }

    @PostMapping("/pricepackage/save")
    public String savePricepackage(Pricepackage pricepackage, RedirectAttributes ra) {
        pricepackageService.save(pricepackage);
        ra.addFlashAttribute("message", "The pricepackage has been saved successfully.");
        return "redirect:/admin/subject-pricepackage";
    }

    @GetMapping("/admin/subject-pricepackage/edit")
    public String showEditFormPricepackage(@RequestParam("id") Integer id, @RequestParam(name = "subject-id") Integer subjectId, HttpSession session, Model model, RedirectAttributes ra) {
        try {
            Pricepackage pricepackage = iPricepackageService.getPricepackageById(id);
            model.addAttribute("hiddenId", pricepackage.getId());
            model.addAttribute("name", pricepackage.getName());
            model.addAttribute("duration", pricepackage.getAccessDuration());
            model.addAttribute("status", pricepackage.getStatus());
            model.addAttribute("listPrice", pricepackage.getListPrice());
            model.addAttribute("salePrice", pricepackage.getSalePrice());
            model.addAttribute("description", pricepackage.getDescription());
            model.addAttribute("subjectId", subjectId);
            model.addAttribute("userSession", session.getAttribute("user"));

            model.addAttribute("action", "edit");
            return "price_package/price_package_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "price_package/price_package_form";
        }
    }

    @PostMapping("/admin/subject-pricepackage/form-submit")
    public String pricePackageFormSubmit(@RequestParam(name = "id") Integer id,
                                         @RequestParam(name = "name", defaultValue = "") String nameNotTrim,
                                         @RequestParam(name = "duration", defaultValue = "1") Integer duration,
                                         @RequestParam(name = "status", defaultValue = "true") String status,
                                         @RequestParam(name = "list-price", defaultValue = "0") Double listPrice,
                                         @RequestParam(name = "sale-price", defaultValue = "0") Double salePrice,
                                         @RequestParam(name = "description", defaultValue = "") String descriptionNotTrim,
                                         @RequestParam(name = "subject-id", defaultValue = "1") Integer subjectId,
                                         @RequestParam(name = "action", defaultValue = "add") String action,
                                         RedirectAttributes redirectAttributes,
                                         Model model,
                                         HttpSession session) {
        System.out.println("id = " + id);
        System.out.println("status: " + status);
        System.out.println("action: " + action);
        System.out.println("subject-id = " + subjectId);

        String name = nameNotTrim.trim();
        String description = descriptionNotTrim.trim();

        //Validation
        String ms1 = "";
        String ms2 = "";
        String ms3 = "";
        String ms4 = "";
        String ms5 = "";
        boolean check = true;
        if(name.isEmpty()) {
            ms1 = "Invalid Input";
            check = false;
        }
        if(description.isEmpty()){
            ms2 = "Invalid Input";
            check = false;
        }
        if((salePrice <= 0) || (listPrice <= 0)) {
            ms4 = "Invalid Price";
            check = false;
        }
        if((duration <= 0 && duration != -1)) {
            ms5 = "Invalid Duration";
        }
        if(salePrice > listPrice) {
            ms4 = "Invalid Price";
            check = false;
        }
        if(!check) {
            if(action.equals("edit")) {
                redirectAttributes.addFlashAttribute("id", id);
                redirectAttributes.addFlashAttribute("name", name);
                redirectAttributes.addFlashAttribute("description", description);
                redirectAttributes.addFlashAttribute("duration", duration);
                redirectAttributes.addFlashAttribute("status", status);
                redirectAttributes.addFlashAttribute("listPrice", listPrice);
                redirectAttributes.addFlashAttribute("salePrice", salePrice);
                redirectAttributes.addFlashAttribute("ms1", ms1);
                redirectAttributes.addFlashAttribute("ms2", ms2);
                redirectAttributes.addFlashAttribute("ms3", ms3);
                redirectAttributes.addFlashAttribute("ms4", ms4);
                redirectAttributes.addFlashAttribute("ms5", ms5);

                return "redirect:/admin/subject-pricepackage/" + action + "?id=" + id + "&subject-id=" + subjectId;

            }
            if(action.equals("add")) {
                redirectAttributes.addFlashAttribute("id", id);
                redirectAttributes.addFlashAttribute("name", name);
                redirectAttributes.addFlashAttribute("description", description);
                redirectAttributes.addFlashAttribute("duration", duration);
                redirectAttributes.addFlashAttribute("status", status);
                redirectAttributes.addFlashAttribute("listPrice", listPrice);
                redirectAttributes.addFlashAttribute("salePrice", salePrice);
                redirectAttributes.addFlashAttribute("ms1", ms1);
                redirectAttributes.addFlashAttribute("ms2", ms2);
                redirectAttributes.addFlashAttribute("ms3", ms3);
                redirectAttributes.addFlashAttribute("ms4", ms4);
                redirectAttributes.addFlashAttribute("ms5", ms5);

                return "redirect:/admin/subject-pricepackage/new?subject-id=" + subjectId;
            }
        }

        //Add new
        if(action.equals("add")) {
            System.out.println("Doing add new subject");

            Pricepackage pricepackage = new Pricepackage();
            Subject subject = iSubjectService.getById(subjectId);
            pricepackage.setName(name);
            pricepackage.setAccessDuration(duration);
            pricepackage.setStatus(true);
            pricepackage.setListPrice(listPrice.floatValue());
            pricepackage.setSalePrice(salePrice.floatValue());
            pricepackage.setDescription(description);
            pricepackage.setSubject(subject);

            iPricepackageService.save(pricepackage);
            System.out.println("Add New Price Package Successful");

            redirectAttributes.addFlashAttribute("newPricePackage", pricepackage);

            return "redirect:/admin/subject-pricepackage?id=" + subjectId;
        }

        //Update current
        if(action.equals("edit")) {
            System.out.println("Doing edit subject");

            Pricepackage selectedPricepackage = iPricepackageService.getPricepackageById(id);
            selectedPricepackage.setName(name);
            selectedPricepackage.setAccessDuration(duration);
            selectedPricepackage.setStatus(true);
            selectedPricepackage.setListPrice(listPrice.floatValue());
            selectedPricepackage.setSalePrice(salePrice.floatValue());
            selectedPricepackage.setDescription(description);

            iPricepackageService.save(selectedPricepackage);
            System.out.println("Update Price Package Successful");
            return "redirect:/admin/subject-pricepackage?id=" + subjectId;
        }

        return "redirect:/admin/subject-pricepackage?id=" + subjectId;
    }

    @GetMapping("/admin/subject-pricepackage/delete")
    public String deletePricepackage(@RequestParam("id") Integer id, RedirectAttributes ra) {
        try {
            pricepackageService.delete(id);
            ra.addFlashAttribute("message", "The pricepackage ID " + id + " has been deleted.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/subject-pricepackage";
    }

    @GetMapping("expert/subject-detail")
    public String ExpertGetToSubjectDetails(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                                            @RequestParam(name = "id", required = true) Integer id,
                                            @RequestParam(name = "categoriesId", required = false) Integer[] selectedCategoriesId,
                                            @RequestParam(name = "status", defaultValue = "-1") String status,
                                            @RequestParam(name = "check", defaultValue = "false") Boolean check,
                                            Model model, HttpSession session) {

        User loggedinUser = (User)session.getAttribute("user");
        if(loggedinUser != null) {
            System.out.println(loggedinUser.getRole().getName());
            String userRoleForUrl = switch (loggedinUser.getRole().getName()) {
                case "ROLE_ADMIN" -> "admin";
                case "ROLE_EXPERT" -> "expert";
                case "ROLE_SALE" -> "sale";
                case "ROLE_MARKETING" -> "marketing";
                case "ROLE_CUSTOMER" -> "user";
                default -> "";
            };
            System.out.println("userRoleForUrl: " + userRoleForUrl);
            model.addAttribute("userRoleForUrl", userRoleForUrl);
        }

        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        model.addAttribute("userSession", session.getAttribute("user"));
        return "course_content/subjectdetails";

    }

    @GetMapping("/expert/subject-details-edit")
    public String ExpertEditSubjectDetails(@RequestParam(name = "id", required = true) Integer id, Model model) {
        Subject subject = iSubjectService.getSubjectById(id);
        model.addAttribute("subject", subject);
        return "course_content/edit_subjectdetails";
    }

    @GetMapping("/expert/subject-dimension")
    public String ExpertGetToDimensionListPage(Model model) {
        List<DimensionDTO> dimension = (List<DimensionDTO>) iDimensionService.getAllDimension();
        model.addAttribute("dimension", dimension);
        return "course_content/dimension";
    }

    @GetMapping("/expert/subject-pricepackage")
    public String ExpertGetToPricepackage(@RequestParam(name = "sid", required = true) Integer sid, Model model) {
        Pricepackage pricepackage = iPricepackageService.getPricepackageBySubId(sid);
        model.addAttribute("pricepackage", pricepackage);
        return "course_content/pricepackage";
    }
}