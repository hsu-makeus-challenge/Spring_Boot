package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Store;
import umc.study.domain.User;
import umc.study.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;


    @Override
    public void writeReview(User user, Store store, float score, String content) {
        reviewRepository.writeReview(user,store,score,content);
    }
}
