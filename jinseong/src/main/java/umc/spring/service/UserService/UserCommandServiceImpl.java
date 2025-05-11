package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserFoodCategory;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.NotificationRepository;
import umc.spring.repository.PointHistoryRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.UserDTO.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public void deleteUserWithRelatedEntities(Long userId) {
        // 1. 알림 기록 삭제
        notificationRepository.deleteByUserId(userId);

        // 2. 포인트 이력 삭제
        pointHistoryRepository.deleteByUserId(userId);

        // 3. 사용자 삭제
        userRepository.deleteUserById(userId);
    }

    @Override
    @Transactional
    public User joinMember(UserRequestDTO.JoinDto request) {

        User newUser = UserConverter.toUser(request);

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserFoodCategory> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
