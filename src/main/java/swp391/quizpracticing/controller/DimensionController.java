package swp391.quizpracticing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swp391.quizpracticing.dto.response.DimensionsResponse;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.LessonProperties;
import swp391.quizpracticing.repository.IDimensionRepository;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class DimensionController {
    private  final IDimensionRepository iDimensionRepository;
    @GetMapping("/api/get-dimensions")
    public DimensionsResponse getDimension(@RequestParam("subjectId") Integer subjectId,
                                           @RequestParam("sortBy") LessonProperties.SortType sortBy){
        List<Dimension> bySubjectsId = iDimensionRepository.findBySubjects_Id(subjectId);
        return DimensionsResponse.of(bySubjectsId.stream()
                .filter(d -> sortBy == LessonProperties.SortType.DOMAIN
                        ? d.getType().equals("domain")
                        : d.getType().equals("group"))
                .toList()
        );
    }
}
