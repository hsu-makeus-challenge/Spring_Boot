package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDTO.writeResultDTO towriteResultDTO(Review review){
        return ReviewResponseDTO.writeResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.WriteReviewDTO request, Store store, User user) {

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(request.getContent())
                .score(request.getScore())
                .build();

        List<ReviewImage> imageEntities = request.getImageUrlList().stream()
                .map(url -> ReviewImage.builder()
                        .imageUrl(url)
                        .review(review)
                        .build())
                .toList();

        review.getReviewImageList().addAll(imageEntities);

        return review;
    }

    public static ReviewResponseDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewList) {
        return ReviewResponseDTO.MyReviewListDTO.builder()
                .reviewList(reviewList.getContent().stream().map(ReviewConverter::toMyReviewDTO).toList())
                .listSize(reviewList.getSize())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .build();

    }

    public static ReviewResponseDTO.MyReviewDTO toMyReviewDTO(Review review){
        return ReviewResponseDTO.MyReviewDTO.builder()
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
