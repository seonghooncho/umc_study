package umc.study.web.controller.memberController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.domain.Member;
import umc.study.service.memberService.MemberCommandService;
import umc.study.web.dto.request.MemberRequestDTO;
import umc.study.web.dto.response.MemberResponseDTO;
import jakarta.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    //@Valid : @NotNull, @Size, @Email 등 기반으로 유효성 검사 후 없으면 MethodArgumentNotValidException 발생
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }
}