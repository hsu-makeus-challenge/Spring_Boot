package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Neighborhood;

public interface NeighborhoodRepository extends JpaRepository<Neighborhood, Long> {
}
