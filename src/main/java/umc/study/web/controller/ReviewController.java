package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import umc.study.apiPayload.ApiResponse;
import umc.study.service.reviewService.command.ReviewCommandService;
import umc.study.web.dto.request.ReviewRequestDTO;

@Controller
@AllArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<?> createReview(@RequestBody @Valid ReviewRequestDTO.CreateDTO dto) {
        reviewCommandService.addReview(dto);
        return ApiResponse.onSuccess();
    }

}
