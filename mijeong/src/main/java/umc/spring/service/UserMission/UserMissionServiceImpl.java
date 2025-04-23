package umc.spring.service.UserMission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserMissionServiceImpl implements UserMissionQueryService {
    private final UserMissionRepository userMissionRepository;

    private static final Integer PAGE_SIZE=3;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdAt").descending());
    }

    @Override
    public Page<UserMission> findMissionsByStatus(Long userId, MissionStatus status, Integer pageNumber) {
        Page<UserMission> missions = userMissionRepository.findUserMissionsByStatus(userId, status, pageRequest(pageNumber));
        return missions;
    }
}
