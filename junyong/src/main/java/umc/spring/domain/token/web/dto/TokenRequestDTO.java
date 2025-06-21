package umc.spring.domain.token.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class TokenRequestDTO {

    @Getter
    public static class RefreshDto {
        @NotNull
        String refreshToken;
    }

}
