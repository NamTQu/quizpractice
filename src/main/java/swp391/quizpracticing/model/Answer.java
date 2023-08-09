package swp391.quizpracticing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "explanation")
    private String explanation;

    @Column(name="checking")
    private Boolean check;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private Question question;
    
    public Answer(String explanation, Boolean check, Boolean status, Question question) {
        this.explanation = explanation;
        this.check = check;
        this.status = status;
        this.question = question;
    }
    
}

