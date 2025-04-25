package umc.spring.integrationTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.entity.enums.Provider;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.member.service.MemberService;
import umc.spring.domain.photo.entity.Photos;
import umc.spring.domain.photo.entity.enums.PhotoType;
import umc.spring.domain.photo.repository.PhotoRepository;
import umc.spring.domain.question.entity.Answer;
import umc.spring.domain.question.entity.Question;
import umc.spring.domain.question.entity.enums.QuestionType;
import umc.spring.domain.question.repository.AnswerRepository;
import umc.spring.domain.question.repository.QuestionRepository;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreReviewRepository;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class MemberServiceImplTest {
  @Autowired private MemberRepository memberRepository;
  @Autowired private PhotoRepository photoRepository;
  @Autowired private AnswerRepository answerRepository;
  @Autowired private QuestionRepository questionRepository;
  @Autowired private ReviewRepository reviewRepository;
  @Autowired private StoreReviewRepository storeReviewRepository;
  @Autowired private MemberService memberService;
  private Long memberId;

  @BeforeEach
  void setUp() {
    Member dummyMember =
        Member.builder()
            .nickname("test_user")
            .email("test@example.com")
            .name("홍길동")
            .gender(Gender.FE)
            .birth(LocalDate.of(1995, 5, 10))
            .address("서울특별시 강남구")
            .addressDetail("101동 202호")
            .provider(Provider.GOOGLE)
            .socialId("kakao123456")
            .status(MemberStatus.ACTIVE)
            .eventNotify(true)
            .reviewNotify(true)
            .qaNotify(false)
            .foodCategory(List.of(FoodCategory.KOREAN, FoodCategory.JAPANESE))
            .build();

    memberRepository.save(dummyMember);
    memberId = dummyMember.getId();

    Photos dummyPhoto =
        Photos.builder()
            .photoUrl("https://example.com/images/photo1.jpg")
            .photoType(PhotoType.REVIEW)
            .directory("review")
            .memberId(memberId)
            .build();
    photoRepository.save(dummyPhoto);

    Question question =
        questionRepository.save(
            Question.builder()
                .memberId(memberId)
                .title("질문입니다")
                .content("내용입니다")
                .questionType(QuestionType.MEMBERINFO)
                .build());

    answerRepository.save(Answer.builder().questionId(question.getId()).content("답변입니다").build());
  }

  @Test
  @DisplayName("deleteMemberAndAllRelatedData : member 배치 삭제 테스트")
  void deleteMemberAndAllRelatedData_shouldDeleteAllRelatedEntities() {
    // when
    memberService.deleteMemberAndAllRelatedData(memberId);

    // then
    assertThat(memberRepository.findById(memberId)).isEmpty();
    assertThat(photoRepository.existsByMemberId(memberId)).isEqualTo(false);
    assertThat(questionRepository.existsByMemberId(memberId)).isEqualTo(false);
    assertThat(answerRepository.findAll()).isEmpty();
    assertThat(reviewRepository.existsByMemberId(memberId)).isEqualTo(false);
    assertThat(storeReviewRepository.findAll()).isEmpty();
  }
}
