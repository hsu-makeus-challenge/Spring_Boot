package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.MissionRequestDTO;
import umc.study.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionResultDTO toDto(Mission mission){
        return MissionResponseDTO.MissionResultDTO.builder()
                .missionId(mission.getId())
                .storeId(mission.getStore().getId())
                .title(mission.getTitle())
                .body(mission.getBody())
                .rewardPoint(mission.getRewardPoint())
                .created_at(mission.getCreatedAt())
                .updated_at(mission.getUpdatedAt())
                .build();
    }

    public static Mission toMission(Store store, MissionRequestDTO.MissionDto request) {
        return Mission.builder()
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .rewardPoint(request.getRewardPoint())
                //.startDate(request.getStartDate())
                //.endDate(request.getEndDate())
                .build();
    }

    public static MissionResponseDTO.MissionDTO missionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .storeName(mission.getStore().getName()) // Mission 엔티티와 Store 연관 관계를 고려하여 수정
                .foodCategory(mission.getStore().getName())
                .rewardPoint(mission.getRewardPoint())
                .build();
    }
    public static MissionResponseDTO.MissionListDTO missionListDTO(Page<Mission> missionList) {
        List<MissionResponseDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(MissionConverter::missionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }
}
