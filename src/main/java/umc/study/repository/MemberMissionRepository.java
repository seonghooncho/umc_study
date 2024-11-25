package umc.study.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    @Query("SELECT mm.mission FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId")
    Page<Mission> findMissionsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

    @Query("SELECT mm.member FROM MemberMission mm " +
            "WHERE mm.mission = :mission")
    Member findMemberByMission(@Param("memberId") Mission mission);
}