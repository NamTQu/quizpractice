package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.quizpracticing.model.Blogcategory;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogcategoryDTO {
    private Integer id;
    private String name;
    private Boolean status;
    private List<BlogDTO>blogs;
    private SettingsDTO setting;
    public void entityToDTO(Blogcategory blogCategory) {
        this.id = blogCategory.getId();
        this.name = blogCategory.getName();
        this.status = blogCategory.getStatus();
    }
}
