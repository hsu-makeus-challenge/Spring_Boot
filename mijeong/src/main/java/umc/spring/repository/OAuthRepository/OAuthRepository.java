package umc.spring.repository.OAuthRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.OAuth;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
}
