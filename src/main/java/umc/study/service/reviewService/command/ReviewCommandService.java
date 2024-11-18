package umc.study.service.reviewService.command;

import umc.study.web.dto.request.ReviewRequestDTO;

public interface ReviewCommandService {
    public void addReview(ReviewRequestDTO.CreateDTO dto);
}
