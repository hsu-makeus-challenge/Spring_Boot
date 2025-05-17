package umc.spring.domain.store.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.region.validation.annotation.RegionEntity;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.service.StoreCommandService;
import umc.spring.domain.store.service.StoreQueryService;
import umc.spring.domain.store.validation.annotation.ExistStore;
import umc.spring.domain.store.web.dto.StoreRequestDTO;
import umc.spring.domain.store.web.dto.StoreResponseDTO;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.validation.annotation.PageValid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping
    public ApiResponse<StoreResponseDTO.addResultDto> addStore(@RequestBody StoreRequestDTO.addStoreDto request, @RegionEntity Region region) {
        Store store = storeCommandService.addStore(request, region);
        return ApiResponse.onSuccess(StoreConverter.toResultDto(store));
    }

    // 특정 가게 리뷰 목록 조회
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 을오 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH03", description = "access 토근을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토근 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토근 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, Path variable")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDto> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDto(reviewList));
    }

    // 특정 가게 미션 목록
    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 을오 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, Path variable"),
            @Parameter(name = "page", description = "페이지 번호. 1이상. Query String")
    })
    public ApiResponse<StoreResponseDTO.MissionListDto> getMissions(@ExistStore @PathVariable("storeId") Long storeId, @PageValid Integer page) {
        StoreResponseDTO.MissionListDto missionList = storeQueryService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(missionList);
    }

}
