package umc.study.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
