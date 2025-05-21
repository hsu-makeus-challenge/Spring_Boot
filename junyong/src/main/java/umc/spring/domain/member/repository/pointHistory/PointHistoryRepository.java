package umc.spring.domain.member.repository.pointHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.member.data.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {

}
