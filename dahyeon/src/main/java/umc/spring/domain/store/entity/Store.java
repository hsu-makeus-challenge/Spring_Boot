package umc.spring.domain.store.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.global.common.BaseTimeEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Builder.Default
  @Column(nullable = false)
  private Float score = 0.0f;

  @Column(nullable = false)
  private LocalDate openTime;

  @Column(nullable = false)
  private LocalDate closeTime;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private Float latitude;

  @Column(nullable = false)
  private Float longitude;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private FoodCategory category;

  @OneToMany(mappedBy = "store")
  @Builder.Default
  private List<Mission> missions = new ArrayList<>();

  public void updateScore(Float newScore) {
    this.score = newScore;
  }
}
