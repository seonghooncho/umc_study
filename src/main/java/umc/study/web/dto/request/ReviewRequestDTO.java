package umc.study.web.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {
    @Getter
    public static class CreateDTO{

        @NotBlank
        private String body;

        @Min(0)
        @Max(5)
        private Float score;

        @ExistStore
        private Long storeId;
        @NotNull
        private Long memberId;


        public Review toReview(Store store, Member member) {
            return Review.builder()
                    .body(this.body)
                    .score(this.score)
                    .store(store)
                    .member(member)
                    .build();
        }
    }
}