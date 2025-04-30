package umc.spring.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository.StoreRepositoryCustom;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}