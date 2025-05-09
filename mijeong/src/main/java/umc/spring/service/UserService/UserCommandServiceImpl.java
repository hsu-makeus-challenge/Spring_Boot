package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.OAuthConverter;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserPretendFoodConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.OAuth;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserPretendFood;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring.repository.OAuthRepository.OAuthRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final OAuthRepository oauthRepository;

    // 회원가입
    @Transactional
    @Override
    public UserResponse.JoinResultDto joinUser(UserRequest.JoinDto requestDto) {
        // 유저 생성
        User user = UserConverter.toUser(requestDto);

//        // N+1
//        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
//                .map(category -> {
//                    return foodCategoryRepository.findById(category).orElseThrow(() -> new ErrorHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
//                }).collect(Collectors.toList());

        // 음식 카테고리 추출
        List<Long> categoryIds = requestDto.getPreferCategory();
        List<FoodCategory> foodCategoryList = foodCategoryRepository.findAllById(categoryIds);

        // 음식 카테고리를 유저 음식 카테고리에 매핑
        List<UserPretendFood> userPreferList = UserPretendFoodConverter.toUserPretendFoodList(foodCategoryList);

        // 유저 - 유저 음식 카테고리 양방향 매핑
        userPreferList.forEach(userPretendFood -> {userPretendFood.setUser(user);});

        // 유저 저장
        userRepository.save(user);

        // 소셜로그인 정보 저장
        OAuth oAuth = OAuthConverter.toOAuth(user, requestDto.getSocialType());
        oauthRepository.save(oAuth);

        log.info("회원가입 완료, userId: {}", user.getId());
        return UserConverter.toJoinResultDTO(user);
    }
}
