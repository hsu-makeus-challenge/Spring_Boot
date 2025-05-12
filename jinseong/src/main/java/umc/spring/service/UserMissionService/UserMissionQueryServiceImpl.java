package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.UserMissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public Page<UserMission> findMissionByUserIdAndUserMissionStatus(Long userId, UserMissionStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));

        return userMissionRepository.dynamicQueryWithBooleanBuilder(userId, status, pageable);
    }

    @Override
    public boolean existsByUserIdAndStoreMissionId(Long userId, Long storeMissionId) {
        return userMissionRepository.existsByUserIdAndStoreMissionId(userId, storeMissionId);
    }
}
