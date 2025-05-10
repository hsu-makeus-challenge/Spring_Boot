package umc.spring.service.ValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ValidationServiceImpl implements ValidationService {
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

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

    // Region 존재 여부 검증
    @Override
    public Boolean existsRegionById(Long regionId) {return regionRepository.existsById(regionId); }

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

    // 지역 반환
    @Override
    public Region validateRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.REGION_NOT_FOUND));
    }
}
