package swp391.quizpracticing.dto;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {
    private Integer id;
    private RoleDTO role;
    private String avatar;
    private String address;
    private String fullName;
    private String password;
    private String email;
    private Boolean gender;
    private String mobile;
    private String token;
    private Date lastUpdateDate;
    private Boolean enable;
    private SubjectDTO subject;

    @Override
    public Collection<RoleDTO> getAuthorities() {
        Collection<RoleDTO> roles=new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enable;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enable;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enable;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}


