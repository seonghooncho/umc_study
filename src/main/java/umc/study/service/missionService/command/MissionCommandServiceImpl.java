package umc.study.service.missionService.command;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MissionHandler;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.MemberMissionRepository;
import umc.study.repository.MissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.request.MissionRequestDTO;
@Transactional
@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;


    @Override
    public void addMission(MissionRequestDTO.CreateDTO dto) {
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.STORE_NOT_FOUND));

        missionRepository.save(dto.toMission(store));
    }

    public void completeMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(()->
                new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        if (mission.getMissionSpec().equals("성공"))
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_COMPLETE);

        Member member = memberMissionRepository.findMemberByMission(mission);
        member.addPoint(mission.getReward());
    }
}
