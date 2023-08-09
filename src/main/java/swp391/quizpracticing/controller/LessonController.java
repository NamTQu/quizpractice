package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.repository.*;
import swp391.quizpracticing.service.ILevelService;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/lesson")
public class LessonController {
    @Autowired
    private ILessonRepository iLessonRepository;

    @Autowired
    private IDimensionRepository iDimensionRepository;

    @Autowired
    private ILessontypeRepository iLessontypeRepository;

    @Autowired
    private ILevelRepository iLevelRepository;

    @Autowired
    private ISubjectRepository iSubjectRepository;

    @Autowired
    private ITesttypeRepository iTesttypeRepository;

    @GetMapping("/detail")
    public String getDetail(Model model, @RequestParam(name = "subject_id") Integer subject_id, @RequestParam(name = "id") Integer id, HttpSession session) {
        model.addAttribute("userSession", session.getAttribute("user"));
        Optional<Lesson> optionalLesson = iLessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            model.addAttribute("lesson", lesson);
        }

        List<Dimension> list = iDimensionRepository.findAll();
        List<Lessontype> listLessonType = iLessontypeRepository.findAll();
        List<Level> listLevel = iLevelRepository.findAll();
        List<Subject> listSubject = iSubjectRepository.findAll();
        List<Testtype> listTestType = iTesttypeRepository.findAll();
        model.addAttribute("id", id);
        model.addAttribute("subject_id", subject_id);
        model.addAttribute("listDismension", list);
        model.addAttribute("listLessonType", listLessonType);
        model.addAttribute("listLevel", listLevel);
        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listTestType", listTestType);
        return "/lesson/lesssonDetail";
    }

    @PostMapping("/detail")
    public String updateLesson(Model model,@RequestParam(name = "subject_id") Integer subject_id, @RequestParam(name = "id") Integer id,
                               @RequestParam(name = "noidung", required = false) String content,
                               @RequestParam(name = "video", required = false) String url,
                               @RequestParam(name = "duration", required = false) String duration,
                               @RequestParam(name = "order", required = false) Integer orderID,
                               @RequestParam(name = "status", required = false) Integer status,
                               @RequestParam(name = "dimension", required = false) Integer dimesion,
                               @RequestParam(name = "lessonType", required = false) Integer lessonType,
                               @RequestParam(name = "level", required = false) Integer level,
                               @RequestParam(name = "subject", required = false) Integer subject,
                               @RequestParam(name = "testtype", required = false) Integer testType
    ) {
        Optional<Lesson> optionalLesson = iLessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            lesson.setHtmlContent(content);
            lesson.setVideoLink(url);
            if (duration != null && !duration.isEmpty()) {
                lesson.setDuration(Time.valueOf(LocalTime.parse(url)));
            } else {
                lesson.setDuration(null);
            }
            lesson.setOrder(orderID);
            lesson.setStatus(status == 1);
            lesson.setDimension(iDimensionRepository.findById(dimesion).get());
            lesson.setLessonType(iLessontypeRepository.findById(lessonType).get());
            lesson.setLevel(iLevelRepository.findById(level).get());
            lesson.setSubject(iSubjectRepository.findById(subject).get());
            lesson.setTestType(iTesttypeRepository.findById(testType).get());
            iLessonRepository.save(lesson);
            model.addAttribute("lesson", lesson);
            return "redirect:/subject/lessonList?id=" + subject_id;
        }
        return null;
    }

    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("id") Integer id,
                               @RequestParam("idSub") Integer idSub) {
        Optional<Lesson> optionalLesson = iLessonRepository.findById(id);
        if (optionalLesson.isPresent()) {
            Lesson lesson = optionalLesson.get();
            boolean status = lesson.getStatus();
            lesson.setStatus(!status);
            iLessonRepository.save(lesson);
        }
        return "redirect:/subject/lessonList?id=" + idSub;
    }
    
    @GetMapping("/add")
    public String showAddLessonForm(Model model,@RequestParam(name = "id") Integer subject_id){
        List<Dimension> list = iDimensionRepository.findAll();
        List<Lessontype> listLessonType = iLessontypeRepository.findAll();
        List<Level> listLevel = iLevelRepository.findAll();
        List<Subject> listSubject = iSubjectRepository.findAll();
        List<Testtype> listTestType = iTesttypeRepository.findAll();
        model.addAttribute("subject_id", subject_id);
        model.addAttribute("listDismension", list);
        model.addAttribute("listLessonType", listLessonType);
        model.addAttribute("listLevel", listLevel);
        model.addAttribute("listSubject", listSubject);
        model.addAttribute("listTestType", listTestType);
        model.addAttribute("subject_id", subject_id);
        return "/lesson/addLesson";
    }
    
    @PostMapping("/add")
    public String addLesson(Model model,@RequestParam(name = "subject_id") Integer subject_id,
                               @RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "noidung", required = false) String content,
                               @RequestParam(name = "video", required = false) String url,
                               @RequestParam(name = "duration", required = false) String duration,
                               @RequestParam(name = "order", required = false) Integer orderID,
                               @RequestParam(name = "status", required = false) Integer status,
                               @RequestParam(name = "dimension", required = false) Integer dimesion,
                               @RequestParam(name = "lessonType", required = false) Integer lessonType,
                               @RequestParam(name = "level", required = false) Integer level,
                               @RequestParam(name = "subject", required = false) Integer subject,
                               @RequestParam(name = "testtype", required = false) Integer testType){
        Lesson newLesson = new Lesson();
        newLesson.setName(name);
        newLesson.setHtmlContent(content);
        newLesson.setVideoLink(url);
        if (duration != null && !duration.isEmpty()) {
                newLesson.setDuration(Time.valueOf(LocalTime.parse(url)));
            } else {
                newLesson.setDuration(null);
            }
        newLesson.setOrder(orderID);
        newLesson.setStatus(status == 1);
        newLesson.setDimension(iDimensionRepository.findById(dimesion).get());
        newLesson.setLessonType(iLessontypeRepository.findById(lessonType).get());
        newLesson.setLevel(iLevelRepository.findById(level).get());
        newLesson.setSubject(iSubjectRepository.findById(subject).get());
        newLesson.setTestType(iTesttypeRepository.findById(testType).get());

        Lesson savedLesson = iLessonRepository.save(newLesson);

        model.addAttribute("lesson", savedLesson);

        return "redirect:/subject/lessonList?id=" + subject_id;
    }
}
