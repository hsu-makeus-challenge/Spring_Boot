package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "business_hours")
public class BusinessHours extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    private enum day{
        MON,TUE,WED,THU,FRI,SAT,SUN
    }

    @ColumnDefault("'MON'")
    @Enumerated(EnumType.STRING)
    private day dayOfWeek;

    @ColumnDefault("'T'")
    @Enumerated(EnumType.STRING)
    private TandF isOpen;

    private LocalTime openTime;

    private LocalTime closeTime;

}
