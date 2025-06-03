package umc.spring.domain.token.web.controller;

import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.token.data.RefreshToken;
import umc.spring.domain.token.repository.RefreshTokenRepository;
import umc.spring.domain.token.web.dto.TokenRequestDTO;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping
    @Operation(summary = "액세스 토큰 재발급 API", description = "RefreshToken이 필요합니다")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRequestDTO.RefreshDto request) {
        String refreshToken = request.getRefreshToken();

        if(!jwtTokenProvider.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        RefreshToken savedRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        String email = Jwts.parserBuilder()
                .setSigningKey(jwtTokenProvider.getSigningKey())
                .build()
                .parseClaimsJws(refreshToken)
                .getBody()
                .getSubject();

        if(!savedRefreshToken.getEmail().equals(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token email mismatch");
        }


//         새로운 액세스 토큰 발급
        String newAccessToken = jwtTokenProvider.generateToken(email, "USER");
        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

}
