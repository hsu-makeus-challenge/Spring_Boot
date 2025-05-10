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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ExistUser;
import umc.spring.web.dto.review.ReviewRequest;
import umc.spring.web.dto.review.ReviewResponse;

@Tag(name = "리뷰 페이지", description = "리뷰에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

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
            @Parameter(name = "userId", description = "로그인한 유저의 아이디", example = "1", required = true),
            @Parameter(name = "storeId", description = "리뷰를 등록하려는 가게의 아이디", example = "1", required = true),
    })
    @PostMapping()
    public ApiResponse<ReviewResponse.ReviewCreateResultDto> postReview(@RequestParam(name="userId") @ExistUser Long userId,
                                                  @RequestParam(name="storeId") @ExistStore Long storeId,
                                                  @RequestBody @Valid ReviewRequest.ReviewCreateDto request){
        ReviewResponse.ReviewCreateResultDto response = reviewCommandService.saveReview(userId, storeId, request);
        return ApiResponse.onSuccess(response);
    }
}
