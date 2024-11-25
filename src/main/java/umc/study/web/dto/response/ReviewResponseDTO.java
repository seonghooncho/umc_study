package umc.study.web.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import umc.study.domain.Review;

import java.util.List;

public class ReviewResponseDTO {
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewDTO {
        private Long id;
        private String content;
        private float score;

    }
    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewListDTO {
        private List<ReviewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

}
