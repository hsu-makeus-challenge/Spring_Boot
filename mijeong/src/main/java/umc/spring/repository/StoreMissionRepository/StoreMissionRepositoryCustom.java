package umc.spring.repository.StoreMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.mapping.StoreMission;

public interface StoreMissionRepositoryCustom {
    // 가게의 미션 목록을 페이지네이션으로 조회
    Page<StoreMission> findAllByStoreWithMission(Long storeId, Pageable pageable);
}
