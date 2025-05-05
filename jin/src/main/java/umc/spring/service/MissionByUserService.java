package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.repository.MissionByUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionByUserService {

    private final MissionByUserRepository missionByUserRepository;

    public List<MissionByUser> findMissionByUserIsCompleted(Long userId, Boolean isCompleted){
        return missionByUserRepository.findByUserIdAndIsCompleted(userId, isCompleted);
    }

}
