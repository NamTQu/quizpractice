package swp391.quizpracticing.dto.response;

import lombok.*;
import swp391.quizpracticing.model.Subcategory;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SubCategoryResponse {
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    private static class SubCategoryDTO {
        private Integer id;
        private String name;

        public static SubCategoryDTO of(Subcategory subcategory) {
            return builder().id(subcategory.getId())
                    .name(subcategory.getName())
                    .build();
        }
    }
    private List<SubCategoryDTO> subCategories;
    public static SubCategoryResponse of(List<Subcategory> subcategories) {
        return builder().subCategories(
                subcategories.stream()
                        .map(SubCategoryDTO::of)
                        .collect(Collectors.toList())
        ).build();
    }
}
