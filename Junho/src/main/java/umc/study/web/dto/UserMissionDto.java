package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionDto {
    private long userID;
    private int point;
    private String storeName;
    private String context;
    private Boolean isCleared;

    @Override
    public String toString() {
        return"userID:"+userID+" point:"+point+" store:"+storeName+" context:"+context+" isCleared:"+isCleared;
    }
}
