package umc.spring.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.domain.User;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long>, UserMissionRepositoryCustom {
    boolean existsByUserIdAndStoreMissionId(long userId, Long storeMissionId);

    // Step 1: ID만 페이징해서 조회
    @Query("SELECT um.id FROM UserMission um WHERE um.user = :user AND um.status = :status")
    Page<Long> findUserMissionIdsByUserAndStatus(@Param("user") User user,
                                                 @Param("status") UserMissionStatus status,
                                                 Pageable pageable);

    // Step 2: 페치 조인으로 연관 엔티티 모두 가져오기
    @Query("""
        SELECT um FROM UserMission um
        JOIN FETCH um.storeMission sm
        JOIN FETCH sm.store
        JOIN FETCH sm.mission
        WHERE um.id IN :ids
    """)
    List<UserMission> findUserMissionsWithFetchByIds(@Param("ids") List<Long> ids);
}
