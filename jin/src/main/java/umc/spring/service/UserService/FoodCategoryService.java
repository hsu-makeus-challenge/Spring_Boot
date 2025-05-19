package umc.spring.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
public class FoodCategoryService {

    // Repository 의존성 주입
    private final FoodCategoryRepository foodCategoryRepository;

    // 카테고리 존재 여부 확인
    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}

