package swp391.quizpracticing.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizreviewDTO {
    private Integer id;
    private LessonDTO lesson;
    private UserDTO user;
    private Time duration;
    private Date dateTaken;
}
