package umc.spring.domain.temp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.spring.domain.member.data.PointHistory;
import umc.spring.domain.member.data.enums.PointType;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointHistoryJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<PointHistory> findAll() {
        String sql = "select id, point, balance, is_success, action_type, member_id from point_history";
        return jdbcTemplate.query(sql, (rs, rowNum) -> PointHistory.builder()
                .id(rs.getLong("id"))
                .point(rs.getInt("point"))
                .balance(rs.getInt("balance"))
                .isSuccess(rs.getBoolean("is_success"))
                .actionType(PointType.valueOf(rs.getString("action_type")))
                .build());
    }

}
