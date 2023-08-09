package swp391.quizpracticing.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
    private Integer id;
    private String title;
    private String content;
    private String thumbnail;
    private String briefInfo;
    private Date lastUpdated;
    private Boolean status;
    private Boolean featuring;
    private UserDTO author;
}
