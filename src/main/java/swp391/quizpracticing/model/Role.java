package swp391.quizpracticing.model;


import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;
    
    @OneToMany(mappedBy = "role")
    private List<User> users;
    
    @OneToOne(mappedBy = "role")
    private Settings setting;

    @Column(name = "url_pattern")
    private String urlPattern;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pid")
    private Role pRole;

    @Override
    public String getAuthority() {
        return name;
    }
}

