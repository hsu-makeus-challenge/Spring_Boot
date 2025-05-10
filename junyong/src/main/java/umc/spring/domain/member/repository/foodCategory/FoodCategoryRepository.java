package umc.spring.domain.member.repository.foodCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
