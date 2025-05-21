package umc.spring.integrationTest.review;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreRepository;
import umc.spring.domain.store.service.ReviewService;

@SpringBootTest
@Transactional
@DisplayName("Store 리뷰 조회 통합 테스트")
public class StoreReviewIntegrationTest {

  @Autowired private StoreRepository storeRepository;
  @Autowired private MemberRepository memberRepository;
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private ReviewService reviewService;

  private Store savedStore;
  private Member savedMember;
  private Member otherMember;

  @BeforeEach
  void setUp() {
    savedMember =
        memberRepository.save(
            Member.builder()
                .name("테스터 이름")
                .nickname("테스터 닉네임")
                .email("tester@example.com")
                .gender(Gender.M)
                .birth(LocalDate.of(1990, 1, 1))
                .status(MemberStatus.ACTIVE)
                .build());

    otherMember =
        memberRepository.save(
            Member.builder()
                .name("테스터 이름1")
                .nickname("테스터 닉네임1")
                .email("tester2@example.com")
                .gender(Gender.M)
                .birth(LocalDate.of(1990, 1, 1))
                .status(MemberStatus.ACTIVE)
                .build());

    savedStore =
        storeRepository.save(
            Store.builder()
                .name("통합테스트 가게")
                .score(4.2f)
                .openTime(LocalDate.now())
                .closeTime(LocalDate.now().plusDays(1))
                .address("서울시 강남구")
                .latitude(37.123f)
                .longitude(127.123f)
                .category(FoodCategory.KOREAN)
                .build());

    for (int i = 0; i < 10; i++) {
      reviewRepository.save(
          Review.builder()
              .member(savedMember)
              .store(savedStore)
              .score(4.0f)
              .content("맛있어요! " + i)
              .build());
    }
    for (int i = 0; i < 5; i++) {
      reviewRepository.save(
          Review.builder()
              .member(otherMember)
              .store(savedStore)
              .score(4.0f)
              .content("맛있어요! " + i)
              .build());
    }
  }

  @Test
  @DisplayName("특정 가게의 리뷰를 페이지 단위로 조회할 수 있다")
  void getReviewListTest() {
    // given
    int page = 0;
    Page<Review> reviewPage =
        reviewService.getReviewList(savedMember.getId(), savedStore.getId(), page);

    // when && then
    assertThat(reviewPage).isNotNull();
    assertThat(reviewPage.getContent()).hasSize(10);
    assertThat(reviewPage.getTotalElements()).isEqualTo(15);
    assertThat(reviewPage.getTotalPages()).isEqualTo(2);

    Review review = reviewPage.getContent().get(0);
    assertThat(review.getStore().getId()).isEqualTo(savedStore.getId());
    assertThat(review.getMember().getId()).isEqualTo(savedMember.getId());
  }

  @Test
  @DisplayName("첫 번째 페이지에서 10개의 리뷰를 조회할 수 있다")
  void getReviewList_FirstPage_Returns10Items() {
    // given
    int page = 0;

    // when
    Page<Review> reviewPage =
        reviewService.getReviewList(savedMember.getId(), savedStore.getId(), page);

    // then
    assertThat(reviewPage.getContent()).hasSize(10);
    assertThat(reviewPage.getNumber()).isEqualTo(0);
  }

  @Test
  @DisplayName("두 번째 페이지에서 나머지 5개의 리뷰를 조회할 수 있다")
  void getReviewList_SecondPage_ReturnsRemainingItems() {
    // given
    int page = 1;

    // when
    Page<Review> reviewPage =
        reviewService.getReviewList(savedMember.getId(), savedStore.getId(), page);

    // then
    assertThat(reviewPage.getContent()).hasSize(5);
    assertThat(reviewPage.getNumber()).isEqualTo(1);
  }

  @Test
  @DisplayName("빈 페이지 요청 시 빈 결과를 반환한다")
  void getReviewList_EmptyPage_ReturnsEmptyList() {
    // given
    int page = 2;

    // when
    Page<Review> reviewPage =
        reviewService.getReviewList(savedMember.getId(), savedStore.getId(), page);

    // then
    assertThat(reviewPage.getContent()).isEmpty();
    assertThat(reviewPage.getTotalPages()).isEqualTo(2);
  }

  @Test
  @DisplayName("리뷰가 없는 가게의 경우 빈 페이지를 반환한다")
  void getReviewList_StoreHasNoReviews_ReturnsEmpty() {
    // given
    Store emptyStore =
        storeRepository.save(
            Store.builder()
                .name("빈 가게")
                .score(0.0f)
                .openTime(LocalDate.now())
                .closeTime(LocalDate.now().plusDays(1))
                .address("없는 주소")
                .latitude(0.0f)
                .longitude(0.0f)
                .category(FoodCategory.JAPANESE)
                .build());

    int page = 0;

    // when
    Page<Review> reviewPage =
        reviewService.getReviewList(savedMember.getId(), emptyStore.getId(), page);

    // then
    assertThat(reviewPage).isEmpty();
  }
}
