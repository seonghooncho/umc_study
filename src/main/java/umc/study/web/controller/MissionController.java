package umc.study.web.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.domain.Mission;
import umc.study.service.missionService.command.MissionCommandService;
import umc.study.web.dto.request.MissionRequestDTO;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    @Operation(summary = "미션 생성 API", description = "새로운 미션을 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER_MISSION4001", description = "이미 진행중인 미션입니다.")
    })
    public ApiResponse<?> addMission(@RequestBody @Valid MissionRequestDTO.CreateDTO request) {
        missionCommandService.addMission(request);
        return ApiResponse.onSuccess();
    }

    @PatchMapping("/{missionId}")
    @Operation(summary = "미션 완료 API", description = "미션을 완료 처리합니다.")
    @Parameters({
            @Parameter(name = "missionId", description = "미션의 고유 ID")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER_MISSION4001", description = "이미 진행중인 미션입니다.")
    })
    public ApiResponse<?> completeMission(@PathVariable Long missionId) {
        missionCommandService.completeMission(missionId);
        return ApiResponse.onSuccess();
    }
}
