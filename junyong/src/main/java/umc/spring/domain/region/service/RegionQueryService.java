package umc.spring.domain.region.service;

import umc.spring.domain.region.data.Region;

import java.util.Optional;

public interface RegionQueryService {

    Boolean isExistRegion(String regionCode);

    Optional<Region> getRegionByRegionCode(String regionCode);


}
