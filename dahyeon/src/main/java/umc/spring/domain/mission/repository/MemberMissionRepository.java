package umc.spring.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

  @Query(
      "SELECT mm FROM MemberMission mm "
          + "JOIN FETCH mm.mission m "
          + "JOIN FETCH m.store s "
          + "WHERE mm.member.id = :memberId "
          + "AND mm.status = :status")
  Page<MemberMission> findAllByMemberIdAndStatus(
      @Param("memberId") Long memberId, @Param("status") MissionStatus status, Pageable pageable);

  boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus missionStatus);
}
