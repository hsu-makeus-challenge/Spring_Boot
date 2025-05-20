package umc.spring.domain.store.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.member.entity.Member;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  private Store store;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(nullable = false, length = 1000)
  private String content;

  @Builder.Default
  @Column(nullable = false)
  private Float score = 0.0f;

  @Convert(converter = IntegerListConverter.class)
  @Builder.Default
  private List<Integer> photoList = new ArrayList<>();
}
