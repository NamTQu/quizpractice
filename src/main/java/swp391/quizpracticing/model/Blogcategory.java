package swp391.quizpracticing.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "blogcategory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blogcategory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Boolean status;

    @ManyToMany(mappedBy="categories")
    private List<Blog> blogs;

    @OneToOne(mappedBy = "blogcategory")
    private Settings setting;
}