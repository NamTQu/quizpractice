package swp391.quizpracticing.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import swp391.quizpracticing.dto.LessonDTO;
import swp391.quizpracticing.dto.UserDTO;
import swp391.quizpracticing.model.*;
import swp391.quizpracticing.service.ILessonService;
import swp391.quizpracticing.service.ISubjectService;
import swp391.quizpracticing.service.IUserSubjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import swp391.quizpracticing.dto.SubjectDTO;

@Controller
public class SimulationExamController {

    @Autowired
    private IUserSubjectService iUserSubjectService;

    @Autowired
    private ILessonService iLessonService;

    @Autowired
    private ISubjectService iSubjectService;

    @GetMapping("/user/simulation-exam")
    public String getToSimulationExam(@RequestParam(name = "page", defaultValue = "0") Integer page, Model model, HttpSession session) {

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        //Get all accessed subjects of the logged-in user
        List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());
        System.out.println("Accessed subjects: " + accessedSubjects.size());

        //Get all lesson from the accessed subjects
        List<LessonDTO> accessedLessons = new ArrayList<>();
        for(UserSubject accessdSubject : accessedSubjects) {
            List<LessonDTO> lessons = iLessonService.findAllSimulationExamsBySubjectId(accessdSubject.getSubject().getId());
            accessedLessons.addAll(lessons);
        }
        System.out.println("accessed simulation exam: " + accessedLessons.size());

        Page<LessonDTO> accessedLessonsPagination;
        int totalLessons = accessedLessons.size();

        if(page != 0) {
            page--;
        }

        List<LessonDTO> LessonsPagination = new ArrayList<>();
        int startItem = page*10;
        int toIndex = Math.min(startItem + 10, accessedLessons.size());
        System.out.println("startIndex: " + startItem + ", toIndex: " + toIndex);
        LessonsPagination = accessedLessons.subList(startItem, toIndex);

        accessedLessonsPagination = new PageImpl<>(LessonsPagination, PageRequest.of(page, 10).withSort(Sort.by(Sort.Direction.ASC, "id")), totalLessons);

        model.addAttribute("accessedLessons", accessedLessonsPagination);
        model.addAttribute("accessedSubjects", accessedSubjects);
        model.addAttribute("userSession", session.getAttribute("user"));
        model.addAttribute("page", page);

        return "simulation_exam/simulation_exam";
    }

    @GetMapping("/user/simulation-exam/search")
    public String searchByExamName(Model model, @RequestParam(name = "exam-name", defaultValue = "") String examName, @RequestParam(name = "page", defaultValue = "0") Integer page, HttpSession session) {

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        if(examName.isEmpty()) {
            model.addAttribute("examName", examName);
            return "redirect:/user/simulation-exam";
        } else {
            //Get all accessed subjects and accessed lessons of the logged in user
            List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());
            List<LessonDTO> accessedLessons = new ArrayList<>();
            for(UserSubject accessdSubject : accessedSubjects) {
                List<LessonDTO> lessons = iLessonService.findAllSimulationExamsBySubjectId(accessdSubject.getSubject().getId());
                accessedLessons.addAll(lessons);
            }

            //Find all lesson by exam-name
            List<LessonDTO> result_foundLessons = new ArrayList<>();
            List<LessonDTO> foundedLessons = iLessonService.searchByExamName(examName);
            for(LessonDTO lesson : foundedLessons) {
                for(LessonDTO accessedlesson : accessedLessons) {
                    if(Objects.equals(accessedlesson.getId(), lesson.getId())) {
                        result_foundLessons.add(lesson);
                    }
                }
            }

            Page<LessonDTO> accessedLessonsPagination;
            int totalLessons = result_foundLessons.size();

            if(page != 0) {
                page--;
            }

            List<LessonDTO> LessonsPagination = new ArrayList<>();
            int startItem = page*10;
            int toIndex = Math.min(startItem + 10, result_foundLessons.size());
            System.out.println("startIndex: " + startItem + ", toIndex: " + toIndex);
            LessonsPagination = result_foundLessons.subList(startItem, toIndex);

            accessedLessonsPagination = new PageImpl<>(LessonsPagination, PageRequest.of(page, 10).withSort(Sort.by(Sort.Direction.ASC, "id")), totalLessons);

            model.addAttribute("examName", examName);
            model.addAttribute("accessedLessons", accessedLessonsPagination);
            model.addAttribute("accessedSubjects", accessedSubjects);
            model.addAttribute("page", page);
            model.addAttribute("userSession", session.getAttribute("user"));

            return "simulation_exam/simulation_exam";
        }

    }

    @GetMapping("/user/simulation-exam/filter")
    public String filterBySubject(Model model, @RequestParam(name = "subject-id") Integer subjectId, @RequestParam(name = "page", defaultValue = "0") Integer page, HttpSession session) {

        System.out.println("Filter method is called");

        //Loggedin User (using Session)
        UserDTO loggedinUser = (UserDTO)session.getAttribute("user");

        //Get all accessed subjects of the logged-in user
        List<UserSubject> accessedSubjects = iUserSubjectService.getAllByUserId(loggedinUser.getId());

        if(subjectId == -1) {
            model.addAttribute("accessedSubjects", accessedSubjects);
            return "redirect:/user/simulation-exam";
        } else {
            List<LessonDTO> filteredLessons = iLessonService.findAllSimulationExamsBySubjectId(subjectId);

            //Get the selected subject
            SubjectDTO selectedSubject = iSubjectService.getDTOById(subjectId);
            System.out.println("Selected Subject: " + selectedSubject.getBriefInfo());

            Page<LessonDTO> accessedLessonsPagination;
            int totalLessons = filteredLessons.size();

            if(page != 0) {
                page--;
            }

            List<LessonDTO> LessonsPagination = new ArrayList<>();
            int startItem = page*10;
            int toIndex = Math.min(startItem + 10, filteredLessons.size());
            System.out.println("startIndex: " + startItem + ", toIndex: " + toIndex);
            LessonsPagination = filteredLessons.subList(startItem, toIndex);

            accessedLessonsPagination = new PageImpl<>(LessonsPagination, PageRequest.of(page, 10).withSort(Sort.by(Sort.Direction.ASC, "id")), totalLessons);

            model.addAttribute("accessedLessons", accessedLessonsPagination);
            model.addAttribute("accessedSubjects", accessedSubjects);
            model.addAttribute("selectedSubject", selectedSubject);
            model.addAttribute("userSession", session.getAttribute("user"));
            model.addAttribute("page", page);
            return "simulation_exam/simulation_exam";
        }

    }

}
