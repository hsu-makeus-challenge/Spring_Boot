package umc.study.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.web.dto.UserMissionDto;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryServiceImpl implements UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    @Override
    public List<UserMissionDto> findClearedMissions() {
        return userMissionRepository.findClearedMissionsWithStore();
    }

    @Override
    public List<UserMissionDto> findNotClearedMissions() {
        return userMissionRepository.findNotClearedMissionsWithStore();
    }
}
