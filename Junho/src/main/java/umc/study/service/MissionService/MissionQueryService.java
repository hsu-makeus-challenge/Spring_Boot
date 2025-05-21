package umc.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.web.dto.MissionDto;

import java.util.List;

public interface MissionQueryService{
    List<MissionDto> showMissions();
    Page<Mission> getMissionList(Long StoreId, Integer page);
}
