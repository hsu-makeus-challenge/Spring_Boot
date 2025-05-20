package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.UserConverter;
import umc.study.converter.UserPreferConverter;
import umc.study.domain.Food;
import umc.study.domain.Users;
import umc.study.domain.mapping.FoodPreference;
import umc.study.repository.UserRepository.FoodCategoryRepository;
import umc.study.repository.UserRepository.UserRepository;
import umc.study.web.dto.UserRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public Users joinMember(UserRequestDTO.JoinDto request) {
        Users newUser = UserConverter.toUser(request);
        //userRepository.save(newUser);
        List<Food> foodList = request.getPreferFood().stream()
                .map( category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(()-> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<FoodPreference> foodPreferList = UserPreferConverter.toUserPreferList(foodList);
        foodPreferList.forEach(foodPreference->{foodPreference.setUser(newUser); });
        return userRepository.save(newUser);
    }

}
