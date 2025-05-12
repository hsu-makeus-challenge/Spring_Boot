package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.ErrorHandler;
import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository.RegionRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RegionQueryServiceImpl implements RegionQueryService {

    private final RegionRepository regionRepository;

    // Region 존재 여부 검증
    @Override
    public Boolean existsRegionById(Long regionId) {
        return regionRepository.existsById(regionId);
    }

    // 지역 반환
    @Override
    public Region validateRegion(Long regionId) {
        return regionRepository.findById(regionId)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.REGION_NOT_FOUND));
    }
}
