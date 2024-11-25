package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.response.ReviewResponseDTO;

public  class ReviewConverter {
    public static ReviewResponseDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResponseDTO.ReviewDTO.builder()
                .id(review.getId())
                .content(review.getBody())
                .score(review.getScore())
                .build();
    }
    public static ReviewResponseDTO.ReviewListDTO toReviewPageDTO(Page<Review> reviewList){
        return  ReviewResponseDTO.ReviewListDTO.builder()
                .reviewList(reviewList.getContent().stream().map(ReviewConverter::toReviewDTO).toList())
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .build();

    }
}
