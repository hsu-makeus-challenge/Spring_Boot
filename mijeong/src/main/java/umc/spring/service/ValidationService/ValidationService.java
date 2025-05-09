package umc.spring.service.ValidationService;

public interface ValidationService {
    // 아이디를 통해 존재하는 음식 카테고리인지 확인
    Boolean existsFoodCategoryById(Long id);
}
