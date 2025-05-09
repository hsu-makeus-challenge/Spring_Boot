package umc.spring.service.ValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidationServiceImpl implements ValidationService {
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Boolean existsFoodCategoryById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
