package umc.spring.domain.store.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.dto.ReviewResponseDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.entity.Store;

public class StoreConverter {
  public static Review toReview(ReviewRequestDto request, Store store, Member member) {
    return Review.builder()
        .store(store)
        .member(member)
        .score(request.getScore())
        .content(request.getContent())
        .build();
  }

  public static ReviewResponseDto.ReviewCreateResultDto toReviewResponseDto(Review review) {
    return ReviewResponseDto.ReviewCreateResultDto.builder()
        .reviewId(review.getId())
        .createdAt(review.getCreatedDate())
        .build();
  }

  public static ReviewResponseDto.ReviewPreViewDto toReviewPreViewDto(Review review) {
    return ReviewResponseDto.ReviewPreViewDto.builder()
        .storeName(review.getStore().getName())
        .nickname(review.getMember().getNickname())
        .score(review.getScore())
        .content(review.getContent())
        .createdDate(review.getCreatedDate())
        .build();
  }

  public static ReviewResponseDto.ReviewPreViewListDto toReviewPreViewListDto(
      Page<Review> reviewPage) {
    List<ReviewResponseDto.ReviewPreViewDto> reviewPreViewDtoList =
        reviewPage.stream().map(StoreConverter::toReviewPreViewDto).collect(Collectors.toList());

    return ReviewResponseDto.ReviewPreViewListDto.builder()
        .reviewList(reviewPreViewDtoList)
        .isFirst(reviewPage.isFirst())
        .isLast(reviewPage.isLast())
        .listSize(reviewPreViewDtoList.size())
        .totalPage(reviewPage.getTotalPages())
        .totalElements(reviewPage.getTotalElements())
        .build();
  }
}
