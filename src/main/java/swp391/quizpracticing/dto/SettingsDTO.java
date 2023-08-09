package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettingsDTO {
    private Integer id;
    private Integer roleId;
    private Integer systemsettingId;
    private Integer blogcategoryId;
    private Integer categoryId;
    private Integer testtypeId;
    private Integer questionlevelId;
    private Integer lessontypeId;
    private Integer dimensionId;
    private String description;
    private String type;
    private String value;
    private Integer order;
    private Boolean status;
}
