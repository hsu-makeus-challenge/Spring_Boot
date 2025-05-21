package umc.spring.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.review.converter.ReviewConverter;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.review.repository.ReviewRepository;
import umc.spring.domain.review.web.dto.ReviewResponseDTO;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResponseDTO.ReviewListDto getReviews(Integer page) {

        final int size = 10;

        Member member = memberRepository.findById(1L).get();
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Review> reviews = reviewRepository.findAllByMember(member, pageRequest);

        if(page >= reviews.getTotalPages() && reviews.getTotalElements() != 0) {
            throw new ErrorHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }

        return ReviewConverter.toReviewDtoList(reviews);
    }
}
