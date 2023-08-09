package swp391.quizpracticing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.model.Lesson;
import swp391.quizpracticing.model.Question;
import swp391.quizpracticing.repository.*;
import swp391.quizpracticing.service.IQuestionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import swp391.quizpracticing.Utility.Utility;
import swp391.quizpracticing.dto.ImportDataDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.service.IDimensionService;
import swp391.quizpracticing.service.ILevelService;

@Controller
@RequestMapping("/testingcontent")
public class QuestionController {
    @Autowired
    private IQuestionRepository iQuestionRepository;

    @Autowired
    private IQuestionService iQuestionService;
    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private IDimensionRepository iDimensionRepository;
    @Autowired
    private ILevelRepository iLevelRepository;

    @Autowired
    private ILessonRepository iLessonRepository;
    
    @Autowired
    private ILevelService iLevelService;
    
    @Autowired
    private IDimensionService iDimensionService;

    @GetMapping("/questionList")
    public String getData(Model model,
                          @RequestParam(name = "page",required = false) Optional<Integer> page,
                          @RequestParam(name = "size",required = false) Optional<Integer> size,
                          @RequestParam(name = "lessonID",required = false) Integer lessonID,
                          @RequestParam(name = "dimensionID",required = false) Integer dimensionID,
                          @RequestParam(name = "level",required = false) Integer level,
                          @RequestParam(name = "status",required = false) Integer status,
                          @RequestParam(name = "search",required = false) String search
                          ,HttpSession session) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");
        List<Question> list = iQuestionRepository.findAll();
        if(lessonID != null && lessonID != -1){
            list = list.stream().filter(n -> n.getLessons().contains(new Lesson(lessonID))).collect(Collectors.toList());
            model.addAttribute("lessonID",lessonID);
        }
        if(dimensionID != null && dimensionID != -1){
            list = list.stream().filter(n -> n.getDimension().getId() == dimensionID).collect(Collectors.toList());
            model.addAttribute("dimensionID",dimensionID);
        }
        if(level != null && level != -1){
            list = list.stream().filter(n -> n.getLevel().getId() == level).collect(Collectors.toList());
            model.addAttribute("level",level);
        }
        if(status != null && status != 0){
            boolean check = status == 1;
            list = list.stream().filter(n -> n.getStatus().booleanValue() == check).collect(Collectors.toList());
            model.addAttribute("status",status);
        }
        if(search != null && !search.trim().isEmpty()){
            list = list.stream().filter(n -> n.getContent().toLowerCase().contains(search.trim().toLowerCase())).collect(Collectors.toList());
            model.addAttribute("search",search);
        }
        Page<Question> listPaging = iQuestionService.findPaginated(PageRequest.of(currentPage - 1, pageSize),list);
        model.addAttribute("listPaging", listPaging);
        int totalPages = listPaging.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("lessonList",iLessonRepository.findAll());
        model.addAttribute("dimensionList",iDimensionRepository.findAll());
        model.addAttribute("levelList",iLevelRepository.findAll());
        return "/question/QuestionList.html";
    }
    
    @PostMapping("/import-data")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        // Process the uploaded file (e.g., save it to a location or process the data)
//        Utility utility = new Utility();
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                // Do something with the file bytes (e.g., save it to a database or process the data)
                List<ImportDataDTO> rows = Utility.validateFile(file, iLevelService, iDimensionService);
                if (rows != null && fileName.endsWith(".xlsx")) {
                    model.addAttribute("type", "success");
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonData = objectMapper.writeValueAsString(rows);

                    model.addAttribute("SuccessAddingData", jsonData);
                } else {
                    model.addAttribute("type", "error");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                model.addAttribute("type", "error");
            }
        }
        return "/question/QuestionList.html";
    }

    @PostMapping("/insert-data")
    public ResponseEntity<String> handleInsertData(@RequestBody List<ImportDataDTO> list) {
        try {
            iQuestionService.importQuestion(list);
            return ResponseEntity.ok("{\"status\": \"success\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"error\", \"message\": \"List submission failed!\"}");
        }

    }
    @GetMapping("/download/template.xlsx")
    public ResponseEntity<Resource> downloadExcelFile() {
        // Load the Excel file from the resources folder
        Resource resource = (Resource) new ClassPathResource("static/template.xlsx");

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=template.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
    
}
