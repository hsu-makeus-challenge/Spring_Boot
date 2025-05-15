package umc.spring.repository.StoreFoodCategoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.mapping.StoreFoodCategory;

public interface StoreFoodCategoryRepository extends JpaRepository<StoreFoodCategory, Long> {
}
