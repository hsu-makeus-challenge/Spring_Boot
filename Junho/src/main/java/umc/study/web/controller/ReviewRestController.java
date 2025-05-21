package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.domain.mapping.Review;
import umc.study.service.ReviewService.ReviewServiceImpl;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {
    private final ReviewServiceImpl reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDTO.writeResultDTO> writeReview(
            @RequestBody @Valid ReviewRequestDTO.AddDto request
            ,User user
            ,@ExistStore Store store)
    {
        Review review = reviewService.writeReview(request,user,store);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResponseDTO(review));
    }
}
