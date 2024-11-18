package umc.study.web.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.validation.annotation.NotAlreadyChallenged;

public class MemberMissionRequestDTO {

    @Getter
    public static class CreateDTO{

        @NotAlreadyChallenged
        MemberMissionIdDTO memberMissionIdDTO;

        public MemberMission toMemberMission(Member member, Mission mission){
            return MemberMission.builder()
                    .member(member)
                    .mission(mission)
                    .status(MissionStatus.CHALLENGING)
                    .build();
        }
    }
    @Getter
    public static class MemberMissionIdDTO{
        @NotNull
        private Long memberId; // 회원 ID
        @NotNull
        private Long missionId; // 미션 ID

    }
}
