package umc.spring.repository.UserPretendFoodRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.UserPretendFood;

public interface UserPretendFoodRepository extends JpaRepository<UserPretendFood, Long> {
}
