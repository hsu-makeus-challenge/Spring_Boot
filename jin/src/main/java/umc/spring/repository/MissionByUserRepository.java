package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;

public interface MissionByUserRepository extends JpaRepository<MissionByUser, Long>{

//    @Query("SELECT m FROM MissionByUser m WHERE m.user.id = :userNo AND m.isCompleted = :isCompleted")
//    List<MissionByUser> existsByUserIdAndMissionId(@Param("userNo") Long userNo, @Param("isCompleted") Boolean isCompleted);

    boolean existsByUserAndMission(User user, Mission mission);

}
