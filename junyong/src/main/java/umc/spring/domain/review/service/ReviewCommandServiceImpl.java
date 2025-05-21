package umc.spring.domain.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.review.converter.ReviewConverter;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.repository.ReviewRepository;
import umc.spring.domain.review.web.dto.ReviewRequestDTO;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.repository.StoreRepository;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.StoreHandler;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review createReview(ReviewRequestDTO.addDto request, Long storeId) {

        Member member = memberRepository.findById(1L).orElseThrow(null); // 임시 느낌
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_REQUIRED));
        Review review = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(review);
    }

}
