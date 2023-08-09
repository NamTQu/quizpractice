package swp391.quizpracticing.model;


import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pricepackage")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pricepackage {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "access_duration")
    private Integer accessDuration;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "list_price")
    private Float listPrice;

    @Column(name = "sale_price")
    private Float salePrice;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @OneToMany(mappedBy = "pricePackage")
    private List<UserSubject> userSubjects;
}
