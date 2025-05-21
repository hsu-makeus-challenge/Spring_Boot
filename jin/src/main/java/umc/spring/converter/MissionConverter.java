package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.mission.MissionRequestDTO;
import umc.spring.web.dto.mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.AddResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.AddResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .isReviewed(mission.getIsReviewed())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.AddMissionDto request, Store store) {
        return Mission.builder()
                .content(request.getContent())
                .deadline(request.getDeadline())
                .point(request.getPoint())
                .store(store)
                .isReviewed(false) // 생성시 기본값 설정
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDto missionPreViewDTO(Mission mission) {
        return MissionResponseDTO.MissionPreViewDto.builder()
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .isReviewed(mission.getIsReviewed())
                .build();
    }

    public static MissionResponseDTO.MissionPreViewListDto missionPreViewListDTO(Page<Mission> missionList) {

        List<MissionResponseDTO.MissionPreViewDto> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDto.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();

    }
}
