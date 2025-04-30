package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyInfoDto {
    private String nickname;
    private String email;
    private String phoneNumber;
    private Integer point;

    @Override
    public String toString() {
        return "닉네임:"+nickname+" 이메일:"+email+" 전화번호:"+phoneNumber+" 포인트"+point;
    }
}
