package umc.spring.service.ValidationService;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.domain.User;

public interface ValidationService {
    // 아이디를 통해 존재하는 음식 카테고리인지 확인
    Boolean existsFoodCategoryById(Long id);

    // User 존재 여부 검증
    Boolean existsUserById(Long userId);

    // Store 존재 여부 검증
    Boolean existsStoreById(Long storeId);

    // Region 존재 여부 검증
    Boolean existsRegionById(Long regionId);

    // 유저 반환
    User validateUser(Long userId);

    // 가게 반환
    Store validateStore(Long storeId);

    // 지역 반환
    Region validateRegion(Long regionId);
}
