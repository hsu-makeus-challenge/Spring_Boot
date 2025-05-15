package umc.spring.domain.region.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.region.data.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Boolean existsByRegionCode(String regionCode);
    Optional<Region> findByRegionCode(String regionCode);
}
