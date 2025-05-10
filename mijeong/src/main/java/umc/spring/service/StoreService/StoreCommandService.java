package umc.spring.service.StoreService;

import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;

public interface StoreCommandService {
    // 가게 등록
    StoreResponse.StoreCreateResultDto saveStore(Long regionId, StoreRequest.StoreCreateDto requestDto);
}
