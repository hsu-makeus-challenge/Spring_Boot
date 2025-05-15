package umc.spring.domain.member.repository.memberMission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.mission.data.Mission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

    Optional<MemberMission> findByMissionAndMember(Mission mission, Member member);
}
