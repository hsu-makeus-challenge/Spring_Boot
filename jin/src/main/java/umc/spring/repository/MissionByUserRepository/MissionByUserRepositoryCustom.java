package umc.spring.repository.MissionByUserRepository;

import umc.spring.domain.mapping.MissionByUser;

import java.util.List;

public interface MissionByUserRepositoryCustom {
    List<MissionByUser> dynamicQueryWithBooleanBuilder(Long userNo, Boolean isCompleted);

}
