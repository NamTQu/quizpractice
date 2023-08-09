package swp391.quizpracticing.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "blog")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "brief_info")
    private String briefInfo;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "featuring")
    private Boolean featuring;


    @ManyToMany
    @JoinTable(name="blog_category",
            joinColumns=@JoinColumn(name="blog_id"),
            inverseJoinColumns=@JoinColumn(name="category_id"))
    private List<Blogcategory> categories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User author;



    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

//    public List<Blogcategory> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Blogcategory> categories) {
//        this.categories = categories;
//    }


}