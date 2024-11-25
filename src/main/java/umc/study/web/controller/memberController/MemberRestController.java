package umc.study.web.controller.memberController;

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
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions/challenge")
    public ApiResponse<?> challengeMission(@RequestBody @Valid MemberMissionRequestDTO.CreateDTO dto) {
        memberCommandService.challengeMission(dto);
        return ApiResponse.onSuccess();
    }

    @GetMapping("/{memberId}/mission")
    public ApiResponse<?> getMissionByMemberId(@PathVariable("memberId") Long memberId,
                                               @CheckPage @RequestParam Integer page) {
        Page<Mission> missions = memberQueryService.findMissionByMemberId(memberId, page);
        return ApiResponse.onSuccess(MissionConverter.toMissionPageDTO(missions));
    }
}