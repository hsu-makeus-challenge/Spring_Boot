package umc.spring.service.RegionService;

import umc.spring.domain.Region;

public interface RegionQueryService {

    // Region 존재 여부 검증
    Boolean existsRegionById(Long regionId);

    // 지역 반환
    Region validateRegion(Long regionId);
}
