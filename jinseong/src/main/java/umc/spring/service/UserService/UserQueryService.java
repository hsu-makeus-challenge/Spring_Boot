package umc.spring.service.UserService;

import com.querydsl.core.Tuple;

public interface UserQueryService {
    Tuple findUserInfoById(Long userId);
}
