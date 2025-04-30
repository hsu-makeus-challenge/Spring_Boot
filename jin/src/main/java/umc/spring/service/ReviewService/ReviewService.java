package umc.spring.service.ReviewService;

public interface ReviewService {
    void writeReview(Long storeId, Float rate, String content);

}
