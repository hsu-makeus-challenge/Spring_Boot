package umc.spring.service.ValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidationServiceImpl implements ValidationService {
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 아이디를 통해 존재하는 음식 카테고리인지 확인
    @Override
    public Boolean existsFoodCategoryById(Long id) {
        return foodCategoryRepository.existsById(id);
    }

    // User 존재 여부 검증
    @Override
    public Boolean existsUserById(Long userId) { return userRepository.existsById(userId); }

    // Store 존재 여부 검증
    @Override
    public Boolean existsStoreById(Long storeId) { return storeRepository.existsById(storeId); }

    // 유저 반환
    @Override
    public User validateUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.USER_NOT_FOUND));
    }

    // 가게 반환
    @Override
    public Store validateStore(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.STORE_NOT_FOUND));
    }
}
