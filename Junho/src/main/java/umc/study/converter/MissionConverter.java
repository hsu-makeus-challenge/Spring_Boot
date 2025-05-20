package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.web.dto.MissionResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission){
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .context(mission.getContext())
                .point(mission.getPoint())
                .context(mission.getContext())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionList){
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream().map(MissionConverter::toMissionPreviewDTO).collect(Collectors.toList());
        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isLast())
                .totalElements(missionList.getTotalElements())
                .totalPages(missionList.getTotalPages())
                .listSize(missionList.getSize())
                .missionList(missionPreviewDTOList)
                .build();
    }

}


