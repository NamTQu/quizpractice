package swp391.quizpracticing.dto.response;

import lombok.*;
import swp391.quizpracticing.model.Dimension;
import swp391.quizpracticing.model.LessonProperties;
import swp391.quizpracticing.model.Subcategory;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LessonPropertiesResponse implements Serializable {
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    private static class LessonPropertyDTO implements Serializable {
        private LessonProperties.SortType sortBy;
        private Integer subCategoryId;
        private Integer dimensionId;
        private Integer noQuestion;

        public static LessonPropertyDTO of(LessonProperties lessonProp) {
            return builder().sortBy(lessonProp.getSortType())
                    .subCategoryId(Optional.of(lessonProp)
                            .map(LessonProperties::getSubcategory)
                            .map(Subcategory::getId).orElse(-1))
                    .dimensionId(Optional.of(lessonProp)
                            .map(LessonProperties::getDimension)
                            .map(Dimension::getId).orElse(-1))
                    .noQuestion(lessonProp.getNoQuestion())
                    .build();
        }
    }

    private List<LessonPropertyDTO> lessonProperties;

    public static LessonPropertiesResponse of(List<LessonProperties> lessonProperties) {
        return builder().lessonProperties(
                lessonProperties.stream()
                        .map(LessonPropertyDTO::of)
                        .collect(Collectors.toList())
        ).build();
    }
}
