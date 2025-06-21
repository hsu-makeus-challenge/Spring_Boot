package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.TandF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "region_detail")
    private String regionDetail;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(name ="phone_num" ,nullable = true, unique = true, length = 20)
    private String phoneNumber;

    public enum Gender{
        M,F,Other
    }

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ColumnDefault("'M'")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String password;

    @Column(columnDefinition = "tinyint default 'T'")
    @Enumerated(EnumType.STRING)
    private TandF marketingConsent;

    @Column(columnDefinition = "tinyint default 'T'")
    @Enumerated(EnumType.STRING)
    private TandF LocationConsent;

    public enum Active{
        Active, Inactive
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @ColumnDefault("'Active'")
    @Enumerated(EnumType.STRING)
    private Active status;

    @Column(name = "new_event_alarm",columnDefinition = "tinyint default 'T'")
    private TandF newEventAlarm;

    @Column(name = "review_rely_alarm", columnDefinition = "tinyint default 'T'")
    private TandF reviewRelyAlarm;

    @Column(name = "inquriy_rely_alarm",columnDefinition = "tinyint default 'T'")
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

    public void encodePassword(String password) {
        this.password = password;
    }
}
