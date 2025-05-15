package umc.spring.repository.StoreImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.StoreImage;

public interface StoreImageRepository extends JpaRepository<StoreImage, Long> {
}
