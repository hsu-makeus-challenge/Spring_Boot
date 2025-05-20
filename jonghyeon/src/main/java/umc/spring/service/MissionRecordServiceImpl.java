package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.apiPayload.exception.GeneralException;
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
public class MissionRecordServiceImpl implements MissionRecordService {

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
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        MissionRecord newMissionRecord = MissionRecordConverter.toMissionRecord(request , user, mission);

        return missionRecordRepository.save(newMissionRecord);

    }
    @Transactional
    @Override
    public Page<Mission> getInProgressMissionsByUserId(Long userId, Integer page) {
        Pageable pageable = PageRequest.of(page, 10); // 페이지 사이즈 조절 가능
        return missionRecordRepository.getInProgressMissionsByUserId(
                userId,
                MissionRecord.Status.Progress,
                pageable
        );
    }
//    @Override
//    @Transactional(readOnly = true)
//    public Page<Mission> getIsProgressMissionByUserId(Long userId, Integer page){
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
//
//        MissionRecord missionRecord = missionRecordRepository.findByUserId(userId)
//                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_RECORD_NOT_FOUND));
//
//        return missionRecordRepository.findAllByUser(user, PageRequest.of(page,10));
//    }
}
