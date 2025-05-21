package umc.spring.domain.mission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.stream.IntStream;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@SpringBootTest
@Transactional
@DisplayName("MissionRepository의 Page vs Slice 반환 비교 테스트")
class MissionRepositoryPaginationComparisonTest {

  @Autowired private MissionRepository missionRepository;
  @Autowired private StoreRepository storeRepository;
  @Autowired private MissionServiceImpl missionService;
  @Autowired private MemberRepository memberRepository;

  private Long testStoreId;

  @BeforeEach
  void setUp() {
    Store store =
        Store.builder()
            .name("Slice 비교 테스트 가게")
            .score(4.5f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("서울시")
            .latitude(37.0f)
            .longitude(127.0f)
            .category(FoodCategory.KOREAN)
            .build();

    Store savedStore = storeRepository.save(store);
    testStoreId = savedStore.getId();

    IntStream.rangeClosed(1, 15)
        .forEach(
            i -> {
              Mission mission =
                  Mission.builder()
                      .description("미션 " + i)
                      .rewardPoint(100 + i)
                      .minAmount(10000 + i)
                      .store(savedStore)
                      .build();
              missionRepository.save(mission);
            });
  }

  @Test
  @DisplayName("Page와 Slice의 반환 값 차이를 비교한다")
  void testComparePageAndSlice() {
    int page = 0;
    int size = 10;
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

    Page<Mission> pageResult = missionRepository.findAllByStore_Id(testStoreId, pageable);
    Slice<Mission> sliceResult = missionRepository.findSliceByStore_Id(testStoreId, pageable);

    assertEquals(pageResult.getContent().size(), sliceResult.getContent().size(), "페이지 크기가 같아야 함");
    assertEquals(pageResult.getContent(), sliceResult.getContent(), "리스트 내용도 같아야 함");

    System.out.println("Page totalElements: " + pageResult.getTotalElements());
    System.out.println("Page totalPages: " + pageResult.getTotalPages());
    System.out.println("Page hasNext: " + pageResult.hasNext());

    System.out.println("Slice hasNext: " + sliceResult.hasNext());

    assertTrue(pageResult.getTotalElements() > 0, "Page는 전체 개수를 제공해야 함");
  }
}
