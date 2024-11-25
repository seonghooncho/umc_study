package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.web.dto.response.MissionResponseDTO;

public class MissionConverter {
    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .id(mission.getId())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .missionSpec(mission.getMissionSpec())
                .build();
    }
    public static MissionResponseDTO.MissionListDTO toMissionPageDTO(Page<Mission> missionList){
        return  MissionResponseDTO.MissionListDTO.builder()
                .missionDTOList(missionList.getContent().stream().map(MissionConverter::toMissionDTO).toList())
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .build();

    }
}
