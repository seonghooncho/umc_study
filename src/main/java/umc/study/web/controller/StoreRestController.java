package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MissionConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.service.StoreService.StoreQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.request.StoreRequestDTO;
import umc.study.web.dto.response.MissionResponseDTO;
import umc.study.web.dto.response.StoreResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;
    private final StoreCommandService storeCommandService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 쿼리스트링 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList= storeQueryService.getReviewList(storeId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @PostMapping
    @Operation(summary = "가게 추가 API",description = "가게를 추가하는 api입니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REGION4001", description = "없는 지역 입니다.")})
    public ApiResponse<?> addStore(@RequestParam @Valid StoreRequestDTO.CreateDTO dto) {
        Store store=  storeCommandService.addStore(dto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "미션조회 API",description = "미션을 가게기준으로 조회하는 api입니다")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "PAGE는 0보다 작을 수 없습니다.")})
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 쿼리스트링 입니다!")
    })
    public ApiResponse<MissionResponseDTO.MissionListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                               @CheckPage @RequestParam(name = "page") Integer page){
        Page<Mission> reviewList= storeQueryService.getMissionList(storeId,page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPageDTO(reviewList));
    }
}

