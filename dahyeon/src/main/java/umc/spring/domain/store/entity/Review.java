package umc.spring.domain.store.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.global.common.BaseTimeEntity;
import umc.spring.global.converter.IntegerListConverter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "store_id", nullable = false)
  private Long storeId;

  @Column(name = "member_id", nullable = false)
  private Long memberId;

  @Column(nullable = false, length = 1000)
  private String content;

  @Builder.Default
  @Column(nullable = false)
  private Float score = 0.0f;

  @Convert(converter = IntegerListConverter.class)
  @Builder.Default
  private List<Integer> photoList = new ArrayList<>();
}
