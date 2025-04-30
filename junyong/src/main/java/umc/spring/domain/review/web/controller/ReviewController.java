package umc.spring.domain.review.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.review.service.ReviewCommandService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public void addReview() {
        String content = "내용내뇽";
        Float score = 3.5f;
        Long storeId = 1L;
        reviewCommandService.writeReview(content, score, storeId);
    }

}
