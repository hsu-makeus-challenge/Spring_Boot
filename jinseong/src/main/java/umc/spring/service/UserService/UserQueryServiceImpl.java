package umc.spring.service.UserService;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.domain.QUser;
import umc.spring.domain.Review;
import umc.spring.domain.User;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserRepository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Tuple findUserInfoById(Long userId) {

        Tuple userInfo = userRepository.dynamicQueryWithBooleanBuilder(userId);

        System.out.println("nickname: " + userInfo.get(QUser.user.nickname));
        System.out.println("email: " + userInfo.get(QUser.user.email));
        System.out.println("phoneNumber: " + userInfo.get(QUser.user.phoneNumber));
        System.out.println("verified: " + userInfo.get(QUser.user.verified));
        System.out.println("point: " + userInfo.get(QUser.user.point));

        return userInfo;
    }

    @Override
    public Page<Review> getReviewList(Long userId, Integer page) {
        // 1) 사용자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));


        Pageable pageable = PageRequest.of(page, 10);


        // 2) 리뷰 ID 조회
        Page<Long> reviewIdPage = reviewRepository.findReviewIdsByUser(user, pageable);

        List<Long> reviewIds = reviewIdPage.getContent();

        if (reviewIds.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }


        // 3) 리뷰 + 리뷰 이미지 조회
        List<Review> reviews = reviewRepository.findReviewsByIdWithImages(reviewIds);

        return new PageImpl<>(reviews, pageable, reviewIdPage.getTotalElements());
    }
}
