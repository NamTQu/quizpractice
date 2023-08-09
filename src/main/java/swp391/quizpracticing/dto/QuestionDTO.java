package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private Integer id;
    private List<AnswerDTO> answer;
    private DimensionDTO dimension;
    private LevelDTO questionLevelId;
    private String content;
    private Boolean status;
    private String image;
    private String audio;
    private String video;
    private List<LessonDTO> lessons;
    private List<SubcategoryDTO> subCategories;
    private List<QuizreviewQuestionDTO> quizreviewQuestions;
}
