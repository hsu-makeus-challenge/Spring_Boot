package umc.spring.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.OAuthConverter;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserPretendFoodConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.OAuth;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserPretendFood;
import umc.spring.repository.OAuthRepository.OAuthRepository;
import umc.spring.repository.UserPretendFoodRepository.UserPretendFoodRepository;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.service.FoodCategoryService.FoodCategoryQueryService;
import umc.spring.web.dto.user.UserRequest;
import umc.spring.web.dto.user.UserResponse;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final OAuthRepository oauthRepository;
    private final UserPretendFoodRepository userPretendFoodRepository;
    private final FoodCategoryQueryService foodCategoryQueryService;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @Transactional
    @Override
    public UserResponse.JoinResultDto joinUser(UserRequest.JoinDto requestDto) {
        // 유저 생성
        User user = UserConverter.toUser(requestDto);
        log.info("email: {}", requestDto.getEmail());
        log.info("password: {}", requestDto.getPassword());
        user.encodePassword(passwordEncoder.encode(requestDto.getPassword()));

        // 유저 저장
        userRepository.save(user);

//        // N+1
//        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
//                .map(category -> {
//                    return foodCategoryRepository.findById(category).orElseThrow(() -> new ErrorHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
//                }).collect(Collectors.toList());

        // 음식 카테고리 추출
        List<Long> categoryIds = requestDto.getPreferCategory();
        List<FoodCategory> foodCategoryList = foodCategoryQueryService.getFoodCategoryList(categoryIds);

        // 음식 카테고리를 유저 음식 카테고리로 매핑
        List<UserPretendFood> userPreferList = UserPretendFoodConverter.toUserPretendFoodList(foodCategoryList);
        // 유저 음식 카테고리에 유저 매핑
        userPreferList.forEach(userPretendFood -> {userPretendFood.setUser(user);});
        userPretendFoodRepository.saveAll(userPreferList);

        // 소셜로그인 정보 저장
        OAuth oAuth = OAuthConverter.toOAuth(user, requestDto.getSocialType());
        oauthRepository.save(oAuth);

        log.info("회원가입 완료, userId: {}", user.getId());
        return UserConverter.toJoinResultDTO(user);
    }

    // 로그인
    @Override
    public UserResponse.LoginResultDto loginUser(UserRequest.LoginRequestDto requestDto, HttpServletResponse response) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new ErrorHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);
        response.setHeader("Authorization", "Bearer " + accessToken);

        log.info("로그인 완료, userId: {}, Access Token: {}", user.getId(), accessToken);
        return UserConverter.toLoginResultDto(
                user.getId()
        );
    }
}
