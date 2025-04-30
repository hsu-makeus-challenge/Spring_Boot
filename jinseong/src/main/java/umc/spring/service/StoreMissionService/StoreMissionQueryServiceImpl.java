package umc.spring.service.StoreMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.repository.StoreMissionRepository.StoreMissionRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreMissionQueryServiceImpl implements StoreMissionQueryService {

    private final StoreMissionRepository storeMissionRepository;

    @Override
    public Page<StoreMission> findStoreMissionsByNeighborhoodAndUser(Long neighborhoodId, Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));

        return storeMissionRepository.dynamicQueryWithBooleanBuilder(neighborhoodId, userId, pageable);
    }
}
