package umc.study.repository.MissionRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMission;
import umc.study.domain.QStore;
import umc.study.web.dto.MissionDto;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;

    @Override
    public List<MissionDto> showMissions(){
        return jpaQueryFactory
                .select(Projections.constructor(
                        MissionDto.class,
                        mission.id,
                        store.name,
                        store.storeCategory,
                        mission.context,
                        mission.point,
                        mission.createdAt
                )).from(mission)
                .join(mission.store, store)
                .fetch();
    }
}

