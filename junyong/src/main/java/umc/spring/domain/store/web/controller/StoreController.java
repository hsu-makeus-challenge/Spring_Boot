package umc.spring.domain.store.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.region.validation.annotation.RegionEntity;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.service.StoreCommandService;
import umc.spring.domain.store.web.dto.StoreRequestDTO;
import umc.spring.domain.store.web.dto.StoreResponseDTO;
import umc.spring.global.common.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.addResultDto> addStore(@RequestBody StoreRequestDTO.addStoreDto request, @RegionEntity Region region) {
        Store store = storeCommandService.addStore(request, region);
        return ApiResponse.onSuccess(StoreConverter.toResultDto(store));
    }

}
