package swp391.quizpracticing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swp391.quizpracticing.dto.response.SubCategoryResponse;
import swp391.quizpracticing.model.Subcategory;
import swp391.quizpracticing.repository.ICategoryRepository;
import swp391.quizpracticing.repository.ISubcategoryRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {
    private final ISubcategoryRepository subcategoryRepository;
    @GetMapping("/api/get-subcategories")
    public SubCategoryResponse getSubCategories(@RequestParam("subjectId") Integer subjectId) {
        List<Subcategory> bySubjectsId = subcategoryRepository.findBySubjects_Id(subjectId);
        return SubCategoryResponse.of(bySubjectsId);
    }
}
