package umc.spring.domain.region.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.region.data.Region;
import umc.spring.domain.region.repository.RegionRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService {

    private final RegionRepository regionRepository;

    @Override
    public Boolean isExistRegion(String regionCode) {
        return regionRepository.existsByRegionCode(regionCode);
    }

    @Override
    public Optional<Region> getRegionByRegionCode(String regionCode) {
        return regionRepository.findByRegionCode(regionCode);
    }


}
