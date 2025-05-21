package umc.spring.service.UserMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.converter.UserMissionConverter;
import umc.spring.domain.User;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.service.UserService.UserQueryService;
import umc.spring.web.dto.userMission.UserMissionResponse;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserMissionQueryServiceImpl implements UserMissionQueryService {
    private final UserMissionRepository userMissionRepository;
    private final UserQueryService userQueryService;

    private static final Integer PAGE_SIZE=10;

    // 페이지 정렬, 최신순
    private Pageable pageRequest(Integer pageNumber) {
        return PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdAt").descending());
    }

    // 미션 상태(진행 중 or 완료)에 따른 유저의 미션 목록 조회
    @Override
    public UserMissionResponse.UserMissionInfoListDto findUserMissionPageByStatus(Long userId, MissionStatus status, Integer pageNumber) {

        // 유저 조회
        User user = userQueryService.validateUser(userId);

        // 미션 상태(진행 중 or 완료)에 따른 유저의 미션 목록을 페이지네이션으로 조회
        Page<UserMission> userMissionPage = userMissionRepository.findUserMissionsByStatus(userId, status, pageRequest(pageNumber));

        return UserMissionConverter.toUserMissionInfoListDto(userMissionPage);
    }

    // 홈 화면 - 선택된 지역에서 도전 가능한(시작 전) 미션 목록 조회
    @Override
    public UserMissionResponse.HomeUserMissionInfoListDto findNotStartedMissionsByRegion(Long userId, Long regionId, MissionStatus status, Integer pageNumber) {
        // 선택한 지역의 도전 가능한(시작 전) 미션 페이지
        Page<UserMission> missionPage = userMissionRepository.findUserMissionsByRegionAndStatus(userId, regionId, status, pageRequest(pageNumber));

        // 최신 10개의 유저 미션 데이터 중 성공인 미션 개수
        Long succeededCount = userMissionRepository.getSucceededMissionCount(userId, regionId);

        return UserMissionConverter.toHomeUserMissionListDto(missionPage, succeededCount);
    }

    // 가게 미션 아이디와 유저 아이디를 통해 UserStoreMission 존재 여부 검증
    @Override
    public Boolean existsUserStoreMissionByStoreMissionIdAndUserId(Long storeMissionId, Long userId) {
        return userMissionRepository.existsByStoreMissionIdAndUserId(storeMissionId, userId);
    }

    // UserMission 존재 여부 검증
    @Override
    public Boolean existsUserMissionById(Long userMissionId) {
        return userMissionRepository.findById(userMissionId).isPresent();
    }

    // 유저 미션 반환
    @Override
    public UserMission validateUserMission(Long userMissionId) {
        return userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_MISSION_NOT_FOUND));
    }
}
