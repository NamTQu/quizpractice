package swp391.quizpracticing.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements GrantedAuthority{
    private Integer id;
    private String name;
    private Boolean status;
    private SettingsDTO setting;

    @Override
    public String getAuthority() {
        return name;
    }
}
