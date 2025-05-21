package umc.spring.domain.mission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.mission.dto.MissionCreateResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.store.entity.Store;

@SpringBootTest
@Transactional
@DisplayName("for문 vs stream")
public class testForVsStreamPerformance {
  @Test
  @DisplayName("for문 vs stream 성능 비교 테스트")
  void testForVsStreamPerformance() {

    Store dummyStore =
        Store.builder()
            .name("테스트 가게")
            .score(4.0f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("서울시 테스트구")
            .latitude(37.0f)
            .longitude(127.0f)
            .category(FoodCategory.KOREAN)
            .build();

    List<Mission> missions =
        IntStream.rangeClosed(1, 100_000)
            .mapToObj(
                i ->
                    Mission.builder()
                        .description("미션 " + i)
                        .rewardPoint(i)
                        .minAmount(1000)
                        .store(dummyStore)
                        .build())
            .toList();

    // for
    long startFor = System.nanoTime();
    List<MissionCreateResponseDto> forList = new ArrayList<>();
    for (Mission mission : missions) {
      forList.add(MissionCreateResponseDto.from(mission));
    }
    long endFor = System.nanoTime();

    // stream
    long startStream = System.nanoTime();
    List<MissionCreateResponseDto> streamList =
        missions.stream().map(MissionCreateResponseDto::from).toList();
    long endStream = System.nanoTime();

    System.out.println("for문 소요 시간: " + (endFor - startFor) / 1_000_000 + " ms");
    System.out.println("stream 소요 시간: " + (endStream - startStream) / 1_000_000 + " ms");

    assertEquals(forList.size(), streamList.size());
  }
}
