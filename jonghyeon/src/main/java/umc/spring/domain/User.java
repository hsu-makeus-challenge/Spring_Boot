package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "region_detail")
    private String regionDetail;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(name ="phone_num" ,nullable = true, unique = true, length = 20)
    private String phoneNumber;

    private enum Gender{
        M,F,Other
    }

    @ColumnDefault("'M'")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String password;

    @ColumnDefault("'T'")
    @Enumerated(EnumType.STRING)
    private TandF marketingConsent;

    @ColumnDefault("'T'")
    @Enumerated(EnumType.STRING)
    private TandF LocationConsent;

    private enum Active{
        Active, Inactive
    }

    @ColumnDefault("'Active'")
    @Enumerated(EnumType.STRING)
    private Active status;

    @ColumnDefault("'T'")
    @Column(name = "new_event_alarm")
    private TandF newEventAlarm;

    @ColumnDefault("'T'")
    @Column(name = "review_rely_alarm")
    private TandF reviewRelyAlarm;

    @ColumnDefault("'T'")
    @Column(name = "inquriy_rely_alarm")
    private TandF inquriyRelyAlarm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;


    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Preference> preferenceList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alarm> alarmList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MissionRecord> missionRecordList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PointRecord> pointRecordList = new ArrayList<>();
}
