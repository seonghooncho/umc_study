package umc.study.service.reviewService.query;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;

public interface ReviewQueryService {
    public Page<Review> getReviews(Long memberId, int page);
}
