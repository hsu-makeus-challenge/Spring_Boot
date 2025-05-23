package umc.study.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Review;
import umc.study.domain.Users;
import umc.study.domain.mapping.UserMission;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.UserMissionRepository.UserMissionRepository;
import umc.study.repository.UserRepository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Page<Review> getReviewList(Integer userId, Integer page){
        Users user = userRepository.findById(userId).get();

        Page<Review> ReviewPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return ReviewPage;
    }
}
