package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.TempConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewQueryService;
import umc.spring.service.TempService.TempQueryService;
import umc.spring.web.dto.TempResponse;
import umc.spring.web.dto.review.ReviewResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){

        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

    @GetMapping("/error")
    public ApiResponse<?> forceInternalServerError() {
        throw new RuntimeException("500 에러 발생");
    }

    @GetMapping("/review/slice")
    public ApiResponse<ReviewResponse.UserReviewPreViewSliceDto> reviewSlice(@RequestParam(name="userId") Long userId,
                                                  @RequestParam(name="page") Integer page) {

        return ApiResponse.onSuccess(reviewQueryService.findUserReviewPageWithSlice(userId, page));
    }
}