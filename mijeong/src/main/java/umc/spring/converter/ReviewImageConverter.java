package umc.spring.converter;

import umc.spring.domain.ReviewImage;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewImageConverter {

    // createDto -> Review Image Entity
    public static List<ReviewImage> toReviewImageList(List<String> reviewImageList) {
        return reviewImageList.stream()
                .map(reviewImage ->
                        ReviewImage.builder()
                                .reviewImageUrl(reviewImage)
                                .build()
                ).collect(Collectors.toList());
    }
}
