package umc.spring.domain.token.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private String email;

    public RefreshToken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }

}
