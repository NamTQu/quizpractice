package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {
    private Integer id;
    private LessontypeDTO lessonType;
    private TesttypeDTO testType;
    private DimensionDTO dimension;
    private String name;
    private Integer order;
    private Boolean status;
    private Double passRate;
    private Time duration;
    private Integer questionNumber;
    private LevelDTO level;
    private String topic;
    private String videoLink;
    private UserDTO creator;
    private SubjectDTO subject;
    private List<QuestionDTO> questions;
    private String htmlContent;
}
