package umc.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MissionByUser;

public interface MissionByUserRepository extends JpaRepository<MissionByUser, Long>{

    boolean existsByUserAndMission(User user, Mission mission);

    Page<MissionByUser> findAllByUserAndIsCompleted(User user, Boolean isCompleted, PageRequest pageRequest);
}
