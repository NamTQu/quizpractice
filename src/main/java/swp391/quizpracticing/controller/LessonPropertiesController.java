package swp391.quizpracticing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swp391.quizpracticing.dto.response.LessonPropertiesResponse;
import swp391.quizpracticing.model.LessonProperties;
import swp391.quizpracticing.repository.ILessonPropertiesRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LessonPropertiesController {
    private final ILessonPropertiesRepository lessonPropertiesRepository;
    @GetMapping("/api/get-lesson-prop")
    public LessonPropertiesResponse getLessonProp(@RequestParam("lessonId") Integer lessonId) {
        List<LessonProperties> byLessonId = lessonPropertiesRepository.findByLesson_Id(lessonId);
        return LessonPropertiesResponse.of(byLessonId);
    }
}
