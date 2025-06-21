package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.config.properties.JwtProperties;
import umc.spring.config.security.jwt.JwtTokenProvider;
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
import umc.spring.web.dto.UserDTO.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private final RedisTemplate<String, String> refreshTokenTemplate;
    private final JwtProperties jwtProperties;


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

        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<UserFoodCategory> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        // 토큰 생성
        String accessToken = jwtTokenProvider.generateToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // 리프레시 토큰 저장
        refreshTokenTemplate.opsForValue().set(
                user.getEmail(),
                refreshToken,
                jwtProperties.getExpiration().getRefresh(),
                TimeUnit.MILLISECONDS
        );


        return UserConverter.toLoginResultDTO(
                user.getId(),
                accessToken, refreshToken
        );
    }

    @Override
    public UserResponseDTO.ReissueTokenDTO reissueAccessToken(String bearerToken) {
        String refreshToken = resolveToken(bearerToken);

        // 1. 토큰 유효성 검사
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new UserHandler(ErrorStatus.INVALID_TOKEN);
        }

        // 2. subject(email) 추출
        String email = jwtTokenProvider.getSubject(refreshToken);

        // 3. Redis에 저장된 refreshToken과 비교
        String storedToken = refreshTokenTemplate.opsForValue().get(email);
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new UserHandler(ErrorStatus.INVALID_TOKEN);
        }

        // 4. 새 AccessToken 생성
        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
        String newAccessToken = jwtTokenProvider.generateToken(authentication);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // 5. Redis 업데이트
        refreshTokenTemplate.opsForValue().set(
                email,
                newRefreshToken,
                jwtProperties.getExpiration().getRefresh(),
                TimeUnit.MILLISECONDS
        );

        return UserConverter.toReissueResultDTO(newAccessToken, newRefreshToken);
    }

    private String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        throw new UserHandler(ErrorStatus.INVALID_TOKEN);
    }
}
