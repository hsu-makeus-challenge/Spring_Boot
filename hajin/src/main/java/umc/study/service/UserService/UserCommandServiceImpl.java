package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.apiPayload.exception.handler.UserHandler;
import umc.study.config.security.jwt.JwtTokenProvider;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.Food;
import umc.study.domain.Users;
import umc.study.domain.mapping.FoodPreference;
import umc.study.repository.UserRepository.FoodCategoryRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Users joinUser(UserRequestDTO.JoinDto request) {
        Users newUser = UserConverter.toUser(request);
        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));
        //userRepository.save(newUser);

//        List<Food> foodList = request.getPreferFood().stream()
//                .map( category -> {
//                    return foodCategoryRepository.findById(category).orElseThrow(()-> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
//                }).collect(Collectors.toList());

        // "1" "2" 형식으로 받은 preferfood를 1, 2 형식으로 변환
        List<Long> categoryIds = request.getPreferFood().stream()
                .map(Long::parseLong)
                .toList();

        // 카테고리 ID를 기반으로 Food 엔티티 조회
        List<Food> foodList = categoryIds.stream()
                .map(categoryId ->
                        foodCategoryRepository.findById(categoryId)
                                .orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND))
                ).toList();

        List<FoodPreference> foodPreferList = UserPreferConverter.toUserPreferList(foodList);
        foodPreferList.forEach(foodPreference->{foodPreference.setUser(newUser); });
        return userRepository.save(newUser);
    }

    @Override
    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO request) {
        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), null,
                Collections.singleton(() -> user.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return UserConverter.toLoginResultDTO(
                Long.valueOf(user.getId()),
                accessToken
        );
    }
}
