package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private Integer id;
    private String explanation;
    private Boolean check;
    private Boolean status;
    private QuestionDTO question;
}
