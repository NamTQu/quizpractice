package swp391.quizpracticing.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    protected Integer id;

    @Column(name = "avatar")
    protected String avatar;

    @Column(name = "full_name")
    protected String fullName;

    @Column(name = "password")
    protected String password;

    @Column(name = "email")
    protected String email;

    @Column(name = "gender")
    protected Boolean gender;

    @Column(name = "mobile")
    protected String mobile;

    @Column(name="address")
    protected String address;

    @Column(name = "token")
    protected String token;

    @Column(name = "last_update_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Timestamp lastUpdateDate;

    @Column(name = "enable")
    protected Boolean enable;

    @OneToMany(mappedBy = "author", cascade=CascadeType.MERGE)
    protected List<Blog> blogs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    protected Role role;

    @OneToMany(mappedBy = "owner", cascade=CascadeType.MERGE)
    protected List<Subject> subject;

    @OneToMany(mappedBy = "creator", cascade=CascadeType.MERGE)
    protected List<Lesson> lessons;

    @OneToMany(mappedBy = "user", cascade=CascadeType.MERGE)
    protected List<Quizreview> quizReviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    protected List<UserSubject> userSubjects;

    @OneToMany(mappedBy = "userUpdate")
    protected List<UserSubject> updatedUserSubject;
    
    @OneToMany(mappedBy = "userCreated")
    protected List<UserSubject> createdUserSubject;

    public User token(String token) {
        setToken(token);
        return this;
    }

    public User lastupdatedate(Timestamp lastUpdateDate) {
        setLastUpdateDate(lastUpdateDate);
        return this;}

    @Override
    public Collection<Role> getAuthorities() {
        Collection<Role> roles=new ArrayList<>();
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
        if(enable==null){
            System.out.println("Email "+email);
            return false;
        }
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

