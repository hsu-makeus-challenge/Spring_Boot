package umc.study.service.UserService;

import umc.study.domain.User;
import umc.study.web.dto.HomeInfoDto;
import umc.study.web.dto.MyInfoDto;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> findUserById(Long userId);
    MyInfoDto getMyInfo(Long userId);
    HomeInfoDto getHomeInfo(Long userId);
}
