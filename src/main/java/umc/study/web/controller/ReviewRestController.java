package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.service.reviewService.command.ReviewCommandService;
import umc.study.service.reviewService.query.ReviewQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.request.ReviewRequestDTO;
import umc.study.web.dto.response.ReviewResponseDTO;

@Controller
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping
    public ApiResponse<?> createReview(@RequestBody @Valid ReviewRequestDTO.CreateDTO dto) {
        reviewCommandService.addReview(dto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/{memberId}")
    public ApiResponse<?> getReviews(@PathVariable Long memberId,
                                     @CheckPage @RequestParam int page) {
        return ApiResponse.onSuccess(ReviewConverter.toReviewPageDTO(reviewQueryService.getReviews(memberId, page)));
    }

}
