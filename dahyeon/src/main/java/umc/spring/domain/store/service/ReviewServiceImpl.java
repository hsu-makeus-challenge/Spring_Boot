package umc.spring.domain.store.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.exception.MemberHandler;
import umc.spring.domain.member.exception.status.MemberErrorStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.store.converter.StoreConverter;
import umc.spring.domain.store.dto.ReviewRequestDto;
import umc.spring.domain.store.entity.Review;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.ReviewRepository;
import umc.spring.domain.store.repository.StoreRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;
  private final MemberRepository memberRepository;
  private final StoreRepository storeRepository;

  @Override
  @Transactional
  public Review createReview(Long memberId, ReviewRequestDto request, Long storeId) {

    Member member =
        memberRepository
            .findById(memberId)
            .orElseThrow(() -> new MemberHandler(MemberErrorStatus.MEMBER_NOT_FOUND));
    Store store = storeRepository.findById(storeId).get();

    Review review = StoreConverter.toReview(request, store, member);
    review = reviewRepository.save(review);

    Float newAverageScore = reviewRepository.calculateAverageScore(store);
    store.updateScore(newAverageScore);
    return review;
  }

  @Override
  public Page<Review> getReviewList(Long memberId, Long storeId, Integer page) {
    memberRepository
        .findById(memberId)
        .orElseThrow(() -> new MemberHandler(MemberErrorStatus.MEMBER_NOT_FOUND));
    return reviewRepository.findAllByStoreId(storeId, PageRequest.of(page, 10));
  }

  @Override
  public Page<Review> getMyReviewList(Long memberId, Integer page) {
    memberRepository
        .findById(memberId)
        .orElseThrow(() -> new MemberHandler(MemberErrorStatus.MEMBER_NOT_FOUND));

    Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdDate"));
    return reviewRepository.findAllByMemberId(memberId, pageable);
  }
}
