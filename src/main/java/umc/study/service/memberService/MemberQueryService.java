package umc.study.service.memberService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;

public interface MemberQueryService {
    public boolean isMissionAlreadyChallenged(Long memberId, Long missionId);
    public Page<Mission> findMissionByMemberId(Long memberId, Integer page);
}
