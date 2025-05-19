package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionRecordConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.MissionRecord;
import umc.spring.domain.User;
import umc.spring.repository.MissionRepository.MissionRecordRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.MissionRecordRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionRecordServiceImpl implements MissionRecoredService {

    private final MissionRecordRepository missionRecordRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public MissionRecord addMissionRecord(
            MissionRecordRequestDTO.addMissionRecordResultDTO request,
            Long missionId,
            Long userId) {

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow();

        User user = userRepository.findById(userId)
                .orElseThrow();

        MissionRecord newMissionRecord = MissionRecordConverter.toMissionRecord(request , user, mission);

        return missionRecordRepository.save(newMissionRecord);

    }
}
