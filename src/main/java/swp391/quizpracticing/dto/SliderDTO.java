package swp391.quizpracticing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swp391.quizpracticing.model.Slider;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SliderDTO {
    private Integer id;
    private String title;
    private String image;
    private String backLink;
    private Boolean status;

    public void sliderHomePage(Slider slider) {
        this.id = slider.getId();
        this.title = slider.getTitle();
        this.image = slider.getImage();
        this.backLink = slider.getBackLink();
        this.status = slider.getStatus();
    }
}
