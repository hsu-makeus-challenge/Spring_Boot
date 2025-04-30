package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfoDto {
    private Long ClearedCnt;
    private int point;

    @Override
    public String toString() {
        return "ClearedCnt=" + ClearedCnt + ", point=" + point;
    }
}
