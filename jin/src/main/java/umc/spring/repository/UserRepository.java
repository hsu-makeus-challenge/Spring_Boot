package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    //굳이 안써도 됨(JpaRepository에서 제공)
    Optional<User> findById(Long userNo);
}
