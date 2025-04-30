package umc.study.repository.UserRepository;

import umc.study.web.dto.HomeInfoDto;
import umc.study.web.dto.MyInfoDto;

public interface UserRepositoryCustom {
    MyInfoDto getMyInfo(Long userId);
    HomeInfoDto getHomeInfo(Long userId);
}
