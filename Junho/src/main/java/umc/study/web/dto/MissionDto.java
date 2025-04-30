package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MissionDto {
    private Long missionId;
    private String storeName;
    private String storeCategory;
    private String context;
    private int point;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "missionId:"+missionId+" storeName:"+storeName+" category:"+ storeCategory +" context:"+context+" point:"+point+" createdAt:"+createdAt;
    }
}
