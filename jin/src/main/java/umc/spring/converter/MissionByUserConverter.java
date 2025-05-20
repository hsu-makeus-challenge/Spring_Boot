package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.mapping.MissionByUser;
import umc.spring.web.dto.mission.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionByUserConverter {

    // 미션 도전
    public static MissionResponseDTO.ChallengeResultDTO toChallengeResultDTO(MissionByUser missionByUser) {
        return MissionResponseDTO.ChallengeResultDTO.builder()
                .missionId(missionByUser.getMission().getId())
                .userId(missionByUser.getUser().getId())
                .createdAt(missionByUser.getCreatedAt())
                .build();
    }

    // 진행중(미완료) 미션
    public static MissionResponseDTO.MissionPreViewDto userMissionPreViewDTO(MissionByUser missionByUser) {
        return MissionResponseDTO.MissionPreViewDto.builder()
                .content(missionByUser.getMission().getContent())
                .point(missionByUser.getMission().getPoint())
                .deadline(missionByUser.getMission().getDeadline())
                .isReviewed(missionByUser.getMission().getIsReviewed())
                .build();
    }


    // 진행중(미완료) 미션 리스트
    public static MissionResponseDTO.MissionPreViewListDto userMissionPreViewListDTO(Page<MissionByUser> missionByUserPage) {

        List<MissionResponseDTO.MissionPreViewDto> missionPreViewDTOList = missionByUserPage.stream()
                .map(MissionByUserConverter::userMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDto.builder()
                .isLast(missionByUserPage.isLast())
                .isFirst(missionByUserPage.isFirst())
                .totalPage(missionByUserPage.getTotalPages())
                .totalElements(missionByUserPage.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
