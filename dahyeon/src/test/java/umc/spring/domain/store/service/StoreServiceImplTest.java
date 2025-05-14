package umc.spring.domain.store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@SpringBootTest
@Transactional
class StoreServiceImplTest {

  @Autowired private StoreService storeService;

  @Autowired private StoreRepository storeRepository;

  @BeforeEach
  void setUp() {
    Store store1 =
        Store.builder()
            .name("테스트 가게 1")
            .score(4.5f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 1")
            .latitude(37.123f)
            .longitude(127.123f)
            .category(FoodCategory.KOREAN)
            .build();

    Store store2 =
        Store.builder()
            .name("테스트 가게 2")
            .score(3.5f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 2")
            .latitude(37.456f)
            .longitude(127.456f)
            .category(FoodCategory.KOREAN)
            .build();

    Store store3 =
        Store.builder()
            .name("테스트 가게 3")
            .score(4.0f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 3")
            .latitude(37.456f)
            .longitude(127.456f)
            .category(FoodCategory.KOREAN)
            .build();

    Store store4 =
        Store.builder()
            .name("테스트 가게 4")
            .score(2.0f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 4")
            .latitude(37.456f)
            .longitude(127.456f)
            .category(FoodCategory.KOREAN)
            .build();

    Store store5 =
        Store.builder()
            .name("테스트 가게 5")
            .score(1.0f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 5")
            .latitude(37.456f)
            .longitude(127.456f)
            .category(FoodCategory.KOREAN)
            .build();

    Store store6 =
        Store.builder()
            .name("테스트 가게 6")
            .score(2.3f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("테스트 주소 6")
            .latitude(37.456f)
            .longitude(127.456f)
            .category(FoodCategory.KOREAN)
            .build();

    storeRepository.save(store1);
    storeRepository.save(store2);
    storeRepository.save(store3);
    storeRepository.save(store4);
    storeRepository.save(store5);
    storeRepository.save(store6);
  }

  //  @Test
  //  @DisplayName("[findAll] 존재하는 가게 조회 ")
  //  void findStore() {
  //    // given
  //    Store savedStore = storeRepository.findAll().get(0);
  //    Long storeId = savedStore.getId();
  //
  //    // when
  //    Optional<Store> findStore = storeService.findStore(storeId);
  //
  //    // then
  //    assertThat(findStore).isPresent();
  //    assertThat(findStore.get().getId()).isEqualTo(storeId);
  //  }

  @Test
  @DisplayName("[findStoresByNameAndScore] 이름으로 가게 조회 ")
  void findStoresByNameAndScore_이름으로_조회() {
    // given
    String name = "테스트 가게 1";

    // when
    List<Store> stores = storeService.findStoresByNameAndScore(name, null);

    // then
    assertThat(stores).hasSize(1);
    assertThat(stores.get(0).getName()).isEqualTo(name);
  }

  @Test
  @DisplayName("[findStoresByNameAndScore] 점수로 가게 조회 ")
  void findStoresByNameAndScore_점수로_조회() {
    // given
    Float score = 2.3f;

    // when
    List<Store> stores = storeService.findStoresByNameAndScore(null, score);

    // then
    assertThat(stores).hasSize(2);
  }

  @Test
  @DisplayName("[findStoresByNameAndScore] 이름이랑 점수로 가게 조회 ")
  void findStoresByNameAndScore_이름과_점수로_조회() {
    // given
    String name = "테스트 가게 1";
    Float score = 4.5f;

    // when
    List<Store> stores = storeService.findStoresByNameAndScore(name, score);

    // then
    assertThat(stores).hasSize(1);
    assertThat(stores.get(0).getName()).isEqualTo(name);
    assertThat(stores.get(0).getScore()).isGreaterThanOrEqualTo(4.5f);
  }
}
