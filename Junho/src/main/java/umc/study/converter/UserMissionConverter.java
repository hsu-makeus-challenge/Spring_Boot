package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.mapping.UserMission;
import umc.study.web.dto.UserMissionDto;
import umc.study.web.dto.UserMissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMissionConverter {
    public static UserMissionResponseDTO.UserMissionPreviewDTO toUserMissionPreviewDTO(UserMission userMission){
        return UserMissionResponseDTO.UserMissionPreviewDTO.builder()
                .point(userMission.getMission().getPoint())
                .storeName(userMission.getMission().getStore().getName())
                .context(userMission.getMission().getContext())
                .isCleared(userMission.getIsCleared())
                .build();
    }

    public static UserMissionResponseDTO.UserMissionPreviewListDTO toUserMissionPreviewListDTO(Page<UserMission> userMissionList){
        List<UserMissionResponseDTO.UserMissionPreviewDTO> userMissionPreviewDTOList = userMissionList.stream().map(UserMissionConverter::toUserMissionPreviewDTO).collect(Collectors.toList());
        return UserMissionResponseDTO.UserMissionPreviewListDTO.builder()
                .isFirst(userMissionList.isFirst())
                .isLast(userMissionList.isLast())
                .totalElements(userMissionList.getTotalElements())
                .totalPages(userMissionList.getTotalPages())
                .listSize(userMissionList.getSize())
                .missionList(userMissionPreviewDTOList)
                .build();
    }

}
