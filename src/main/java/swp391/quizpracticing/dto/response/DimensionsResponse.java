package swp391.quizpracticing.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import swp391.quizpracticing.model.Dimension;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DimensionsResponse {
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    private static class DimensionDTO implements Serializable {
        private Integer id;
        private String name;
        public static DimensionDTO of (Dimension dimension){
            return builder().id(dimension.getId())
                    .name(dimension.getName())
                    .build();
        }
    }
    private List<DimensionDTO> dimensiones;
    public static DimensionsResponse of (List<Dimension> dimensions){
        return builder().dimensiones(dimensions.stream().map(DimensionDTO::of ).collect(Collectors.toList())).build();
    }
}
