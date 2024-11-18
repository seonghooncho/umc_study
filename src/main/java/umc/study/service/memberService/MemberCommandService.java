package umc.study.service.memberService;

import umc.study.domain.Member;
import umc.study.web.dto.request.MemberMissionRequestDTO;
import umc.study.web.dto.request.MemberRequestDTO;

public interface MemberCommandService {
    public Member joinMember(MemberRequestDTO.JoinDto request);
    public void challengeMission(MemberMissionRequestDTO.CreateDTO dto);
}
