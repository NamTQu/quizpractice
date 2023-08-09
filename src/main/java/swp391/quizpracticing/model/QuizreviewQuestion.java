package swp391.quizpracticing.model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quizreview_question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizreviewQuestion {

    @EmbeddedId
    private QuizreviewQuestionKey id;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "bookmark")
    private Boolean bookmark;

    @Column(name = "is_correct")
    private Boolean isCorrect;
}

