//package umc.spring.domain.store.service;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.LocalDate;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import umc.spring.domain.member.entity.Member;
//import umc.spring.domain.member.entity.enums.FoodCategory;
//import umc.spring.domain.store.dto.ReviewRequestDto;
//import umc.spring.domain.store.entity.Review;
//import umc.spring.domain.store.entity.Store;
//import umc.spring.domain.store.repository.ReviewRepository;
//import umc.spring.domain.store.repository.StoreRepository;
//
//@SpringBootTest
//@Transactional
//class ReviewServiceImplTest {
//
//  @Autowired private ReviewService reviewService;
//
//  @Autowired private ReviewRepository reviewRepository;
//
//  @Autowired private StoreRepository storeRepository;
//
//  private Member member;
//  private Store store;
//
//  @BeforeEach
//  void setUp() {
//    member = Member.builder().id(1L).name("테스트 회원").build();
//
//    store =
//        Store.builder()
//            .name("테스트 가게")
//            .score(0.0f)
//            .openTime(LocalDate.now())
//            .closeTime(LocalDate.now().plusDays(1))
//            .address("테스트 주소")
//            .latitude(37.123f)
//            .longitude(127.123f)
//            .category(FoodCategory.KOREAN)
//            .build();
//
//    storeRepository.save(store);
//  }
//
//  @Test
//  @DisplayName("[createReview] 리뷰 작성 성공")
//  void createReview_Success() {
//    // given
//    ReviewRequestDto.CreateReviewDto request = new ReviewRequestDto.CreateReviewDto();
//    request.setStoreId(store.getId());
//    request.setContent("맛있어요!");
//    request.setScore(4.5f);
//
//    // when
//    Review review = reviewService.createReview(member, request);
//
//    // then
//    assertThat(review).isNotNull();
//    assertThat(review.getContent()).isEqualTo("맛있어요!");
//    assertThat(review.getScore()).isEqualTo(4.5f);
//    assertThat(review.getStoreId()).isEqualTo(store.getId());
//    assertThat(review.getMemberId()).isEqualTo(member.getId());
//  }
//
//  @Test
//  @DisplayName("[createReview] 여러 리뷰 작성 시 평균 점수 계산 확인")
//  void createReview_MultipleReviews_AverageScore() {
//    // given
//    ReviewRequestDto.CreateReviewDto request1 = new ReviewRequestDto.CreateReviewDto();
//    request1.setStoreId(store.getId());
//    request1.setContent("첫 번째 리뷰");
//    request1.setScore(4.0f);
//
//    ReviewRequestDto.CreateReviewDto request2 = new ReviewRequestDto.CreateReviewDto();
//    request2.setStoreId(store.getId());
//    request2.setContent("두 번째 리뷰");
//    request2.setScore(5.0f);
//
//    // when
//    reviewService.createReview(member, request1);
//    reviewService.createReview(member, request2);
//
//    // then
//    assertThat(reviewRepository.findAll()).hasSize(2);
//    // TODO : 평균 점수 계산 검증 필요
//  }
//  // TODO : 존재하지 않는 가게에 리뷰 작성 시 실패 검증 필요
//}
