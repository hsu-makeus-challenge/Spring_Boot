package umc.spring.repository.UserRepository;

import umc.spring.domain.User;

public interface UserRepositoryCustom {
    User findUserInfoByUserNo(Long userNo);
}
