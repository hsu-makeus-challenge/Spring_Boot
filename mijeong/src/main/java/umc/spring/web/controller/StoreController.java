package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;


@Tag(name = "가게 페이지", description = "가게에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @Operation(
            summary = "지역에 가게 추가",
            description = "특정 지역에 가게를 추가하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REGION4001", description = "아이디와 일치하는 지역이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping("/region/{regionId}")
    public ApiResponse<StoreResponse.StoreCreateResultDto> postStoreByRegion(
            @Parameter(description = "가게를 등록하려는 지역의 아이디", example = "1", required = true)
            @PathVariable @ExistRegion Long regionId,

            @RequestBody @Valid StoreRequest.StoreCreateDto request
    ) {
        StoreResponse.StoreCreateResultDto response = storeCommandService.saveStore(regionId, request);
        return ApiResponse.onSuccess(response);
    }


    @Operation(
            summary = "리뷰 등록",
            description = "유저가 특정 가게에 리뷰를 등록하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "아이디와 일치하는 가게가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "리뷰를 등록할 가게의 ID", example = "2", required = true),
            @Parameter(name = "userId", description = "로그인한 유저의 ID", example = "1", required = true)
    })
    @PostMapping("/review")
    public ApiResponse<ReviewResponse.ReviewCreateResultDto> postReview(
            @RequestParam @ExistStore Long storeId, @RequestParam @ExistUser Long userId, @RequestBody @Valid ReviewRequest.ReviewCreateDto request
    ) {
        ReviewResponse.ReviewCreateResultDto response = reviewCommandService.saveReview(userId, storeId, request);
        return ApiResponse.onSuccess(response);
    }


    @Operation(
            summary = "가게의 리뷰 목록 조회",
            description = "가게의 리뷰 목록을 페이지네이션으로 조회하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "아이디와 일치하는 가게가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "리뷰를 조회할 가게의 ID", example = "2", required = true)
    })
    @GetMapping("/review")
    public ApiResponse<ReviewResponse.ReviewPreViewListDTO> getReviewList(@RequestParam @ExistStore Long storeId,
                                                                          @RequestParam Integer page) {
        Page<Review> storeReviewPage = reviewQueryService.findReviewList(storeId, page);

        return ApiResponse.onSuccess(ReviewConverter.toReviewPreViewListDTO(storeReviewPage));
    }
}
