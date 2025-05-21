package umc.spring.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.SocialType;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class UserRequest {

    @Getter
    @Schema(description = "회원가입 요청 정보")
    public static class JoinDto{
        @NotNull @Email
        @Schema(description = "이메일", example = "test@gmail.com")
        String email;

        @NotNull
        @Schema(description = "소셜 로그인 타입", example = "GOOGLE")
        SocialType socialType;

        @NotNull @Size(min = 2, max = 20)
        @Schema(description = "닉네임", example = "미정")
        String nickName;

        @NotNull
        @Schema(description = "성별", example = "FEMALE")
        Gender gender;

        @NotNull @Size(min = 2, max = 10)
        @Schema(description = "출생연도", example = "YYYY-MM-DD")
        String birth;

        @NotNull @Size(min = 10, max = 30)
        @Schema(description = "주소", example = "서울시 성북구")
        String address;

        @Size(max = 20)
        @Schema(description = "상세 주소", example = "공학관")
        String addressDetail;

        @NotNull @Size(min = 2, max = 20)
        @Schema(description = "전화번호", example = "010-0000-0000")
        String phone;

        @ExistCategories
        @Schema(description = "선호 음식 카테고리 아이디", example = "[1, 2]")
        List<Long> preferCategory;
    }


}
