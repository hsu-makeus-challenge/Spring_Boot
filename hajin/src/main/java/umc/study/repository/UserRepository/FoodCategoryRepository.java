package umc.study.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.study.domain.Food;

@Repository
public interface FoodCategoryRepository extends JpaRepository<Food, Long> {
}
