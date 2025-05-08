package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Page<Mission> getAvaliableMissionsByRegionId(Long regionId, Pageable pageable) {
        return missionRepository.findAvailableMissionsByRegionId(regionId, LocalDate.now(), pageable);
    }
}
