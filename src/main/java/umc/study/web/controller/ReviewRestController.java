package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Operation(summary = "리뷰 생성 API", description = "사용자가 새로운 리뷰를 생성하는 API.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    public ApiResponse<?> createReview(@RequestBody @Valid ReviewRequestDTO.CreateDTO dto) {
        reviewCommandService.addReview(dto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 리뷰 목록 조회 API", description = "특정 회원의 리뷰 목록을 조회하는 API입니다. 페이징을 지원하며, page 번호를 쿼리 스트링으로 제공합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "PAGE는 0보다 작을 수 없습니다."),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER404", description = "해당 회원을 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 쿼리스트링 입니다!")
    })
    public ApiResponse<?> getReviews(@PathVariable Long memberId,
                                     @CheckPage @RequestParam int page) {
        return ApiResponse.onSuccess(ReviewConverter.toReviewPageDTO(reviewQueryService.getReviews(memberId, page)));
    }

}
