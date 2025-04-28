package umc.spring.domain.member.repository.memberMission;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {

}
