package umc.spring.domain.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.token.data.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByRefreshToken(String token);
}
