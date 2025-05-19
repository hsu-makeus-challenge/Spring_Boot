package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.StoreReviewService;
import umc.spring.validation.ExistStore;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;


@RestController
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
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviewList(@ExistStore @PathVariable(name="storeId") Long storeId, @PathVariable(name="userId") Long userId){
        return null;
    }
}
