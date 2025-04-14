package umc.spring.domain.member.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.List;
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

  private String nickname;

  private String email;

  @Enumerated(EnumType.STRING)
  private MemberStatus status;

  private String name;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private LocalDate birth;
  private String address;
  private String addressDetail;

  @Enumerated(EnumType.STRING)
  private Provider provider;

  private String socialId;
  private LocalDate inactiveDate;
  private Boolean eventNotify;
  private Boolean reviewNotify;
  private Boolean qaNotify;

  @Convert(converter = FoodCategoryConverter.class)
  private List<FoodCategory> foodCategory;
}
