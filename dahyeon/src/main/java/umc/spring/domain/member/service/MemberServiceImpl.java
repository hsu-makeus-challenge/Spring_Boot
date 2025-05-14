package umc.spring.domain.member.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.dto.MemberResponseDto;
import umc.spring.domain.member.dto.MemberSignUpRequestDto;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.exception.MemberHandler;
import umc.spring.domain.member.exception.status.MemberErrorStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.photo.repository.PhotoRepository;
import umc.spring.domain.question.repository.AnswerRepository;
import umc.spring.domain.question.repository.QuestionRepository;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  private final PhotoRepository photoRepository;
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;
  private final ReviewRepository reviewRepository;
  private final StoreReviewRepository storeReviewRepository;

  @Transactional
  public void deleteMemberAndAllRelatedData(Long memberId) {
    // Id 기반 삭제 ( 단순 외래키로 연결되어 있는 경우들.. )
    photoRepository.deleteByMemberId(memberId);
    // 질문 아이디로 답변 조회 후 삭제
    answerRepository.deleteByMemberId(memberId);
    questionRepository.deleteByMemberId(memberId);
    // 리뷰 아이디로 스토어리뷰 조회 후 삭제
    storeReviewRepository.deleteByMemberId(memberId);
    reviewRepository.deleteByMemberId(memberId);

    // 연관관계 있는 엔티티들은 cascade + orphanRemoval로 자동 삭제
    // PontLogs, Notification, Agreement, MemberMission은 자동 삭제됨
    Member member = findById(memberId);
    // 마지막으로 Member 삭제
    memberRepository.delete(member);
  }

  @Override
  public MemberResponseDto.MyPageResponseDto getMyPage(Long memberId) {
    Member member = findById(memberId);
    return MemberResponseDto.MyPageResponseDto.from(member);
  }

  @Override
  @Transactional
  public Member signup(MemberSignUpRequestDto request) {
    Member newMember = MemberConverter.toMember(request);
    return memberRepository.save(newMember);
  }


  public Member findById(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(
        MemberErrorStatus.MEMBER_NOT_FOUND));
  }
}
