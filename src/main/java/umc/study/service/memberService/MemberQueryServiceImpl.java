package umc.study.service.memberService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MemberRepository;

@Service
@Transactional(readOnly=true)
@AllArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private MemberMissionRepository memberMissionRepository;
    @Override
    public boolean isMissionAlreadyChallenged(Long memberId, Long missionId) {
        return memberMissionRepository.existsByMemberIdAndMissionId(memberId, missionId);
    }
}
