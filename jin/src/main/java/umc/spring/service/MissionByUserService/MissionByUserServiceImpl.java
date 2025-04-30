package umc.spring.service.MissionByUserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.repository.MissionByUserRepository.MissionByUserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionByUserServiceImpl implements MissionByUserService{

    private final MissionByUserRepository missionByUserRepository;

    @Override
    public List<MissionByUser> findMissionByUserIsCompleted(Long userNo, Boolean isCompleted){
        List<MissionByUser> filterdMissions = missionByUserRepository.dynamicQueryWithBooleanBuilder(userNo, isCompleted);

        filterdMissions.forEach(missionByUser -> System.out.println("Mission : " + missionByUser));

        return filterdMissions;
    }
}

