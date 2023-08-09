package swp391.quizpracticing.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.quizpracticing.model.Subcategory;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Integer id;
    private String title;
    private String briefInfo;
    private String description;
    private String thumbnail;
    private Boolean status;
    private Date createdTime;
    private Date lastUpdatedTime;
    private UserDTO owner;
    private DimensionDTO dimension;
    private Boolean featured;
    private String tagLine;
    private List<Subcategory> subCategories;
}
