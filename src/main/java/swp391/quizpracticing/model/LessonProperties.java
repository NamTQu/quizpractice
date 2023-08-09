package swp391.quizpracticing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lesson_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonProperties {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",referencedColumnName = "id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id",referencedColumnName = "id")
    private Subcategory subcategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dimension_id",referencedColumnName = "id")
    private Dimension dimension;

    @Column(name = "sortBy")
    private SortType sortType;

    @Column(name = "noQuestion")
    private Integer noQuestion;
    @AllArgsConstructor
    @Getter
    public enum SortType {
        TOPIC (0),
        DOMAIN (1),
        GROUP (2);

        private final int value;
    }
}
