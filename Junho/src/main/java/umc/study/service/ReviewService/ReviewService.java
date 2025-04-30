package umc.study.service.ReviewService;

import umc.study.domain.*;

public interface ReviewService {
    void writeReview(User user, Store store, float score, String content);
}
