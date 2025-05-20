package umc.spring.domain.store.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreRepository;

@SpringBootTest
@Transactional
@DisplayName("ReviewService 내 리뷰 목록 페이징 테스트")
class ReviewServicePaginationTest {

  @Autowired private ReviewServiceImpl reviewService;
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private MemberRepository memberRepository;
  @Autowired private StoreRepository storeRepository;

  private Long testMemberId;
  private Store testStore;

  @BeforeEach
  void setUp() {

    testStore =
        storeRepository.save(
            Store.builder()
                .name("반야마라탕")
                .score(4.5f)
                .openTime(LocalDate.now())
                .closeTime(LocalDate.now().plusDays(1))
                .address("테스트 주소 1")
                .latitude(37.123f)
                .longitude(127.123f)
                .category(FoodCategory.KOREAN)
                .build());

    Member member =
        memberRepository.save(
            Member.builder()
                .nickname("테스트닉네임")
                .email("test@example.com")
                .name("테스트")
                .birth(LocalDate.of(1999, 3, 15))
                .gender(Gender.FE)
                .status(MemberStatus.ACTIVE)
                .build());

    testMemberId = member.getId();

    // 리뷰 30개 저장
    List<Review> reviews =
        IntStream.rangeClosed(1, 30)
            .mapToObj(
                i ->
                    Review.builder()
                        .content("리뷰 내용 " + i)
                        .score((float) (i % 5 + 1))
                        .photoList(List.of(i))
                        .store(testStore)
                        .member(member)
                        .build())
            .collect(Collectors.toList());

    reviewRepository.saveAll(reviews);
  }

  @Test
  @DisplayName("1페이지 조회 시 10개 반환 및 isFirst == true")
  void testPageOne() {
    Page<Review> page = reviewService.getMyReviewList(testMemberId, 1);

    assertEquals(10, page.getContent().size());
    assertEquals(3, page.getTotalPages());
    assertEquals(30, page.getTotalElements());
    assertTrue(page.isFirst());
  }

  @Test
  @DisplayName("마지막 페이지 조회 시 isLast == true")
  void testLastPage() {
    Page<Review> page = reviewService.getMyReviewList(testMemberId, 3);

    assertEquals(10, page.getContent().size());
    assertTrue(page.isLast());
  }

  @Test
  @DisplayName("범위를 초과한 페이지 요청 시 빈 리스트 반환")
  void testOutOfRangePage() {
    Page<Review> page = reviewService.getMyReviewList(testMemberId, 10);

    assertEquals(0, page.getContent().size());
  }

  @Test
  @DisplayName("createdDate 기준 내림차순으로 정렬된 리뷰 목록이 반환되어야 한다.")
  void testReviewOrderByCreatedDateDesc_withContentCheck() {
    // given
    Member member = memberRepository.findById(testMemberId).orElseThrow();

    List<Review> insertedReviews =
        IntStream.rangeClosed(1, 5)
            .mapToObj(
                i -> {
                  Review review =
                      Review.builder()
                          .content("정렬 확인용 리뷰 " + i)
                          .score(5.0f)
                          .store(testStore)
                          .member(member)
                          .build();
                  reviewRepository.save(review);
                  try {
                    Thread.sleep(10);
                  } catch (InterruptedException ignored) {
                  }
                  return review;
                })
            .collect(Collectors.toList());

    // when
    Page<Review> result = reviewService.getMyReviewList(testMemberId, 1);
    List<Review> reviews = result.getContent();

    // then
    for (int i = 0; i < 5; i++) {
      String expected = "정렬 확인용 리뷰 " + (5 - i);
      String actual = reviews.get(i).getContent();
      assertEquals(expected, actual, "최신순 정렬된 리뷰의 content가 예상과 다릅니다.");
    }

    for (int i = 1; i < reviews.size(); i++) {
      LocalDateTime before = reviews.get(i - 1).getCreatedDate();
      LocalDateTime after = reviews.get(i).getCreatedDate();
      assertTrue(
          before.isAfter(after) || before.isEqual(after), "리뷰가 createdDate 기준으로 내림차순 정렬되어야 합니다.");
    }
  }

  @Test
  @DisplayName("가장 최근에 저장한 리뷰가 목록의 첫 번째에 있어야 한다.")
  void testLatestReviewIsFirst() {
    // given
    Member member = memberRepository.findById(testMemberId).orElseThrow();
    Review latestReview =
        reviewRepository.save(
            Review.builder()
                .content("가장 최신 리뷰")
                .score(5.0f)
                .store(testStore)
                .member(member)
                .build());

    // when
    Page<Review> result = reviewService.getMyReviewList(testMemberId, 1);
    Review firstReview = result.getContent().get(0);

    // then
    assertEquals("가장 최신 리뷰", firstReview.getContent());
  }

  @Test
  @DisplayName("전체 리뷰 목록은 createdDate 기준으로 내림차순 정렬되어야 한다.")
  void testCreatedDatesAreInDescendingOrder() {
    // given
    Page<Review> result = reviewService.getMyReviewList(testMemberId, 1);
    List<Review> reviews = result.getContent();

    // then
    for (int i = 1; i < reviews.size(); i++) {
      LocalDateTime before = reviews.get(i - 1).getCreatedDate();
      LocalDateTime after = reviews.get(i).getCreatedDate();
      assertTrue(
          before.isAfter(after) || before.isEqual(after), "리뷰가 createdDate 기준 내림차순으로 정렬되어야 합니다.");
    }
  }

  @Test
  @DisplayName("content 값도 최신순으로 정렬된 createdDate 순서와 일치해야 한다.")
  void testReviewContentOrderMatchesCreatedDateDesc() {
    // given
    Member member = memberRepository.findById(testMemberId).orElseThrow();

    IntStream.rangeClosed(1, 5)
        .forEach(
            i -> {
              reviewRepository.save(
                  Review.builder()
                      .content("정렬 리뷰 " + i)
                      .score(5.0f)
                      .store(testStore)
                      .member(member)
                      .build());
              try {
                Thread.sleep(10);
              } catch (InterruptedException ignored) {
              }
            });

    // when
    Page<Review> result = reviewService.getMyReviewList(testMemberId, 1);
    List<Review> reviews = result.getContent();

    // then
    for (int i = 0; i < 5; i++) {
      String expected = "정렬 리뷰 " + (5 - i);
      assertEquals(expected, reviews.get(i).getContent());
    }
  }

  @Test
  @DisplayName("가장 오래된 리뷰는 첫 페이지의 마지막에 있어야 한다.")
  void testOldReviewIsLastOnFirstPage_withoutEntityHack() {
    // given
    Member member = memberRepository.findById(testMemberId).orElseThrow();

    for (int i = 1; i <= 10; i++) {
      reviewRepository.save(
          Review.builder()
              .content("정렬 확인용 리뷰 " + i)
              .score(5.0f)
              .store(testStore)
              .member(member)
              .build());

      try {
        Thread.sleep(20);
      } catch (InterruptedException ignored) {
      }
    }

    // when
    Page<Review> result = reviewService.getMyReviewList(testMemberId, 1);
    Review lastReviewOnFirstPage = result.getContent().get(9);

    // then
    assertEquals("정렬 확인용 리뷰 1", lastReviewOnFirstPage.getContent());
  }
}
