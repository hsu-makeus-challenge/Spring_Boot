package umc.spring.service.FoodCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.FoodCategory;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FoodCategoryQueryServiceImpl implements FoodCategoryQueryService {

    private final FoodCategoryRepository foodCategoryRepository;

    // 음식 카테고리 추출
    @Override
    public List<FoodCategory> getFoodCategoryList(List<Long> categoryIds) {
        return foodCategoryRepository.findAllById(categoryIds);
    }

    // 아이디를 통해 존재하는 음식 카테고리인지 확인
    @Override
    public Boolean existsFoodCategoryById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
