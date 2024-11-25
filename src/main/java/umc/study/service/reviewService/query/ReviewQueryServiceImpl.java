package umc.study.service.reviewService.query;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Review;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.response.ReviewResponseDTO;

@RequiredArgsConstructor
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public Page<Review> getReviews(Long memberId, int page) {
        return reviewRepository.findAllByMemberId(memberId, PageRequest.of(page,10));
    }
}
