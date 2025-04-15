package umc.spring.domain.member.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.member.converter.FoodCategoryConverter;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.entity.enums.Provider;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.global.common.BaseTimeEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nickname;

  @Column(nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 15)
  @Builder.Default
  private MemberStatus status = MemberStatus.ACTIVE;

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(nullable = false)
  private LocalDate birth;

  private String address;
  private String addressDetail;

  @Enumerated(EnumType.STRING)
  private Provider provider;

  private String socialId;
  private LocalDate inactiveDate;

  @Builder.Default
  @Column(nullable = false)
  private Boolean eventNotify = false;

  @Builder.Default
  @Column(nullable = false)
  private Boolean reviewNotify = false;

  @Builder.Default
  @Column(nullable = false)
  private Boolean qaNotify = false;

  @Convert(converter = FoodCategoryConverter.class)
  private List<FoodCategory> foodCategory;

  @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Builder.Default
  @JoinColumn(name = "member_id")
  private List<Notification> notifications = new ArrayList<>();

  @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Builder.Default
  @JoinColumn(name = "member_id")
  private List<Agreement> agreements = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<PointLog> pointLogs = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private List<MemberMission> memberMissions = new ArrayList<>();

  public void addPointLog(PointLog log) {
    pointLogs.add(log);
    log.assignMember(this);
  }
}
