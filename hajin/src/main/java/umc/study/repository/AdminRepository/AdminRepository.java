package umc.study.repository.AdminRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;

public interface AdminRepository extends JpaRepository<Mission, Long> {
}
