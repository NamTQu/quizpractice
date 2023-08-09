package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class SystemsettingsDTO {
    private Integer id;
    private Integer name;
    private Integer status;
    private SettingsDTO setting;
}
