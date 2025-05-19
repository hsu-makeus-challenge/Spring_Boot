package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.StoreReviewService;
import umc.spring.validation.ExistPage;
import umc.spring.validation.ExistStore;
import umc.spring.validation.ExistUser;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/store/{storeId}/review/{userId}")
public class ReviewRestController {

    private final StoreReviewService storeReviewService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.writeResultDTO> write(
            @RequestBody @Valid ReviewRequestDTO.WriteReviewDTO request,
            @PathVariable Long storeId,
            @PathVariable Long userId) {
        Review review = storeReviewService.writeReview(request, storeId, userId);
        return ApiResponse.onSuccess(ReviewConverter.towriteResultDTO(review));
    }

    @GetMapping("/myReview")
    @Operation(summary = "유저의 가게 리뷰 목록 조회 API",description = "유저가 작성한 특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4001", description = "사용자가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "가게가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "userId", description = "유저의 아이디 임시로 만들어졌습니다, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviewList(
            @ExistStore @PathVariable(name="storeId") Long storeId,
            @ExistUser @PathVariable(name="userId") Long userId,
            @ExistPage @RequestParam(name = "page") Integer page
            ) {
        Page<Review> reviewList = storeReviewService.getMyReviewList(storeId, userId, page);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewList));
    }
}
