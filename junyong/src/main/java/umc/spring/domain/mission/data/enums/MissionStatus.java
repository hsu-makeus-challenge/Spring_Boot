package umc.spring.domain.mission.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MissionStatus {

    CHALLENGING("진행중"), COMPLETE("성공"), FAILED("실패");

    @Getter
    private String status;

}
