package umc.study.web.controller;

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
    public ApiResponse<?> addMission(@RequestBody @Valid MissionRequestDTO.CreateDTO request) {
        missionCommandService.addMission(request);
        return ApiResponse.onSuccess();
    }
}
