package umc.spring.repository.UserMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;

public interface UserMissionRepositoryCustom {
    Page<UserMission> dynamicQueryWithBooleanBuilder(Long userId, UserMissionStatus userMissionStatus, Pageable pageable);
}
