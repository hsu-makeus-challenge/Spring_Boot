package umc.spring.service.StoreMissionService;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.StoreMission;

public interface StoreMissionQueryService {
    Page<StoreMission> findStoreMissionsByNeighborhoodAndUser(Long neighborhoodId, Long userId, int page, int size);
}
