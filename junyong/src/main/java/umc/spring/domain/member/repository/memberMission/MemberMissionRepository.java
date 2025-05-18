package umc.spring.domain.member.repository.memberMission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.mission.data.Mission;
import umc.spring.domain.mission.data.enums.MissionStatus;


import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    Optional<MemberMission> findByMissionAndMember(Mission mission, Member member);
//    @Query("select mm from MemberMission mm " +
//            "join fetch Mission m " +
//            "join fetch Store s " +
//            "where mm.member = :member and mm.status = :status")
    @Query(value = "select mm from MemberMission mm " +
            "join fetch mm.mission m " +
            "join fetch m.store s " +
            "where mm.member = :member and mm.status = :status",
            countQuery = "select count(mm) from MemberMission mm " +
                    "where mm.member = :member and mm.status = :status")
    Page<MemberMission> findByMemberAndStatus(@Param("member")Member member, @Param("status") MissionStatus status, PageRequest pageRequest);

}
