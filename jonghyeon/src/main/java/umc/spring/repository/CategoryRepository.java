package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
