package umc.spring.converter;

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

    public static ReviewResponseDTO.MyReviewListDTO myReviewListDTO(List<Review> reviewList){
        return null;
    }

    public static ReviewResponseDTO.MyReviewDTO myReviewDTO(Review review){
        return null;
    }

}
