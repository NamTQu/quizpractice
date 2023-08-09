package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizreviewQuestionDTO {
    private QuizreviewDTO quizreviewId;
    private QuestionDTO questionId;
    private String userAnswer;
    private Boolean status;
    private Boolean bookmark;
    private  Boolean is_correct;
}
