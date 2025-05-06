package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "point_record")
public class PointRecord extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_record_id")
    private MissionRecord missionRecord;

    public enum RecordType {
        earn,spend
    }


    @ColumnDefault("'earn'")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecordType recordType;

    @Column(nullable = false, length = 255)
    private String reason;


}
