package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.store.StoreRequestDTO;
import umc.spring.web.dto.store.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @PostMapping()
    public ApiResponse<StoreResponseDTO.AddStoreResultDto> addStore(@RequestBody @Valid StoreRequestDTO.AddStoreDto request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResultDto(store));
    }

    @PostMapping("/assign-region")
    public ApiResponse<String> assignRegionToStore(@RequestBody @Valid StoreRequestDTO.AssignRegionDto request) {
        storeCommandService.assignRegionToStore(request);
        return ApiResponse.onSuccess("가게에 지역이 성공적으로 연결되었습니다.");
    }

}

