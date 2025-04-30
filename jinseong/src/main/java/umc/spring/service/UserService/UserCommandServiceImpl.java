package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.NotificationRepository;
import umc.spring.repository.PointHistoryRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Override
    public void deleteUserWithRelatedEntities(Long userId) {
        // 1. 알림 기록 삭제
        notificationRepository.deleteByUserId(userId);

        // 2. 포인트 이력 삭제
        pointHistoryRepository.deleteByUserId(userId);

        // 3. 사용자 삭제
        userRepository.deleteUserById(userId);
    }
}
