package umc.study.service.missionService.command;

import umc.study.domain.Mission;
import umc.study.web.dto.request.MissionRequestDTO;

public interface MissionCommandService {
    public void addMission(MissionRequestDTO.CreateDTO dto);
    public void completeMission(Long missionId);
}
