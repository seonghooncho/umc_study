package umc.study.service.memberService;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.MissionRepository;

@Service
@Transactional(readOnly=true)
@AllArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private MemberMissionRepository memberMissionRepository;
    private MissionRepository missionRepository;
    @Override
    public boolean isMissionAlreadyChallenged(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }

    public Page<Mission> findMissionByMemberId(Long memberId, Integer page) {
        return memberMissionRepository.findMissionsByMemberId(memberId, PageRequest.of(page,10));
    }
}
