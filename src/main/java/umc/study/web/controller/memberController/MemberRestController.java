package umc.study.web.controller.memberController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.MissionConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.service.memberService.MemberCommandService;
import umc.study.service.memberService.MemberQueryService;
import umc.study.validation.annotation.CheckPage;
import umc.study.web.dto.request.MemberMissionRequestDTO;
import umc.study.web.dto.request.MemberRequestDTO;
import umc.study.web.dto.response.MemberResponseDTO;
import jakarta.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    //@Valid : @NotNull, @Size, @Email 등 기반으로 유효성 검사 후 없으면 MethodArgumentNotValidException 발생
    @PostMapping("/")
    @Operation(summary = "회원 가입 API", description = "회원가입하는 api.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공")
    })
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions/challenge")
    @Operation(summary = "회원 미션 도전 API", description = "미션에 도전하는 api.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER_MISSION4001", description = "이미 진행중인 미션입니다.")
    })
    public ApiResponse<?> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.CreateDTO dto) {
        memberCommandService.challengeMission(dto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/{memberId}/mission")
    @Operation(summary = "회원 미션 조회 API", description = "특정 회원의 미션 목록을 조회. 페이징을 지원합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID"),
            @Parameter(name = "page", description = "페이지 번호, 쿼리스트링 입니다!")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "PAGE400", description = "PAGE는 0보다 작을 수 없습니다.")
            })
    public ApiResponse<?> getMissionByMemberId(@PathVariable("memberId") Long memberId,
                                               @CheckPage @RequestParam Integer page) {
        Page<Mission> missions = memberQueryService.findMissionByMemberId(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPageDTO(missions));
    }
}