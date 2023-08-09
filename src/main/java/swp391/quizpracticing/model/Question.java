package swp391.quizpracticing.model;


import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "image")
    private String image;

    @Column(name = "audio")
    private String audio;

    @Column(name = "video")
    private String video;

    @OneToMany(mappedBy = "question")
    private List<Answer> answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dimension_id",referencedColumnName = "id")
    private Dimension dimension;
    
    @ManyToMany(mappedBy = "questions")
    private List<Lesson> lessons;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_level_id",referencedColumnName = "id")
    private Level level;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "question_subcategory",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
    private List<Subcategory> subCategories;
    
    @OneToMany(mappedBy = "id.question")
    private List<QuizreviewQuestion> quizreviewQuestions;

    public Question( String content, Boolean status, String image, String audio, String video, Dimension dimension, Level level) {
        this.content = content;
        this.status = status;
        this.image = image;
        this.audio = audio;
        this.video = video;
        this.dimension = dimension;
        this.level = level;
    }
    
}
