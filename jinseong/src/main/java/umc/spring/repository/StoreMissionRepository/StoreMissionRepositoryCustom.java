package umc.spring.repository.StoreMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.mapping.StoreMission;

public interface StoreMissionRepositoryCustom {
    Page<StoreMission> dynamicQueryWithBooleanBuilder(Long neighborhoodId, Long userId, Pageable pageable);
}
