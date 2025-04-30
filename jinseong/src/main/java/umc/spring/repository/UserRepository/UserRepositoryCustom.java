package umc.spring.repository.UserRepository;

import com.querydsl.core.Tuple;

public interface UserRepositoryCustom {
    Tuple dynamicQueryWithBooleanBuilder(Long userId);
}
