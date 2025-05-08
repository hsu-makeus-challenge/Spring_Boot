package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.mapping.MissionByUser;

import java.util.List;

public interface MissionByUserRepository extends JpaRepository<MissionByUser, Long>{

    @Query("SELECT m FROM MissionByUser m WHERE m.user.id = :userNo AND m.isCompleted = :isCompleted")
    List<MissionByUser> findByUserIdAndIsCompleted(@Param("userNo") Long userNo, @Param("isCompleted") Boolean isCompleted);

}
