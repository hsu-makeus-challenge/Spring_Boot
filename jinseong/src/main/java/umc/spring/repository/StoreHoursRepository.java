package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.StoreHours;

public interface StoreHoursRepository extends JpaRepository<StoreHours, Long> {
}
