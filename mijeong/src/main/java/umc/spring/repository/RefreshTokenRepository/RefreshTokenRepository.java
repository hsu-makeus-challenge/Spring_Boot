package umc.spring.repository.RefreshTokenRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import umc.spring.config.security.jwt.RefreshToken;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
