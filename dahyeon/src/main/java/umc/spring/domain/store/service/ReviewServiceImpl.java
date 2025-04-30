package umc.spring.domain.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public Review createReview(Member member, ReviewRequestDto.CreateReviewDto request) {
    // TODO :  가게 존재 여부 확인 로직 필요함
    //        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new ~~
    // );

    Review review =
        Review.builder()
            .memberId(member.getId())
            .storeId(request.getStoreId())
            .content(request.getContent())
            .score(request.getScore())
            .build();
    Review savedReview = reviewRepository.save(review);

    // TODO : 가게 평점 업데이트 로직 필요함
    //        store.updateScore(reviewRepository.calculateAverageScore(store.getId()));

    return savedReview;
  }
}
