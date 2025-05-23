package umc.study.service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.study.converter.UserMissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserMissionRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMissionServiceImpl implements UserMissionService{

    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional //jakarta
    public UserMission addMission(Integer userId, Long missionId, UserMissionRequestDTO.UserMissionDto request) {
        Users user = userRepository.getReferenceById(userId);
        Mission mission = missionRepository.getReferenceById(missionId);
        UserMission userMission = UserMissionConverter.toUserMission(user,mission, request);
        mission.addUserMission(userMission);
        user.addUserMission(userMission);
        return userMissionRepository.save(userMission);
    }

    @Override
    @Transactional
    public Page<Mission> getMissionList(Integer userId, Integer page){

        Users user = userRepository.findById(userId).get();

        Page<UserMission> userMissionPage = userMissionRepository.findAllByUser(user, PageRequest.of(page, 10));

        List<Mission> missions = userMissionPage
                .stream()
                .map(UserMission::getMission)
                .collect(Collectors.toList());

        // 페이징 정보 가져오기 (원래의 Pageable 활용)
        Pageable pageable = userMissionPage.getPageable();

        // 전체 개수도 UserMission 기준 그대로 사용
        long total = userMissionPage.getTotalElements();

        // List<Mission> → Page<Mission>
        Page<Mission> missionPage = new PageImpl<>(missions, pageable, total);

        return missionPage;
    }
}
