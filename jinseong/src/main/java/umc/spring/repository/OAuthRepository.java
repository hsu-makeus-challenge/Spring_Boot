package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.OAuth;
import umc.spring.domain.enums.Provider;

import java.util.Optional;

@Repository
public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    Optional<OAuth> findBySocialIdAndProvider(String socialId, Provider provider);
}
