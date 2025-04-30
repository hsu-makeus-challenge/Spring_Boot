package umc.spring.domain.mission.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.mission.dto.MissionResponseDto;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.mission.repository.MemberMissionRepository;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@SpringBootTest
@Transactional
class MissionServiceTest {

  @Autowired private MissionService missionService;

  @Autowired private MissionRepository missionRepository;

  @Autowired private StoreRepository storeRepository;

  @Autowired private MemberRepository memberRepository;

  @Autowired private MemberMissionRepository memberMissionRepository;

  private Store store1;
  private Store store2;
  private Store store3;

  private Member testMember;
  private Mission mission1, mission2, mission3;
  private MemberMission memberMission1, memberMission2, memberMission3;

  @BeforeEach
  void setUp() {
    store1 =
        Store.builder()
            .name("강남 맛집")
            .score(4.5f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("서울시 강남구")
            .latitude(37.498095f)
            .longitude(127.027610f)
            .category(FoodCategory.KOREAN)
            .build();

    store2 =
        Store.builder()
            .name("역삼 맛집")
            .score(4.0f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("서울시 강남구")
            .latitude(37.500651f)
            .longitude(127.036245f)
            .category(FoodCategory.KOREAN)
            .build();

    store3 =
        Store.builder()
            .name("판교 맛집")
            .score(4.8f)
            .openTime(LocalDate.now())
            .closeTime(LocalDate.now().plusDays(1))
            .address("경기도 성남시 분당구")
            .latitude(37.400759f)
            .longitude(127.108768f)
            .category(FoodCategory.KOREAN)
            .build();

    storeRepository.saveAll(List.of(store1, store2, store3));

    Mission mission1 =
        Mission.builder()
            .store(store1)
            .description("강남역 미션")
            .rewardPoint(1000)
            .minAmount(10000)
            .build();

    Mission mission2 =
        Mission.builder()
            .store(store2)
            .description("역삼역 미션")
            .rewardPoint(2000)
            .minAmount(20000)
            .build();

    Mission mission3 =
        Mission.builder()
            .store(store3)
            .description("판교역 미션")
            .rewardPoint(3000)
            .minAmount(30000)
            .build();

    Mission deletedMission =
        Mission.builder()
            .store(store1)
            .description("삭제된 미션")
            .rewardPoint(500)
            .minAmount(5000)
            .isDeleted(true)
            .build();

    missionRepository.saveAll(List.of(mission1, mission2, mission3, deletedMission));

    testMember =
        Member.builder()
            .nickname("테스트닉네임")
            .email("test@example.com")
            .name("테스트")
            .birth(LocalDate.of(1999, 3, 15))
            .gender(Gender.FE)
            .status(MemberStatus.ACTIVE)
            .build();
    memberRepository.save(testMember);

    memberMission1 =
        MemberMission.builder()
            .member(testMember)
            .mission(mission1)
            .status(MissionStatus.PROGRESS)
            .build();
    memberMission2 =
        MemberMission.builder()
            .member(testMember)
            .mission(mission2)
            .status(MissionStatus.COMPLETE)
            .completedAt(LocalDate.now())
            .build();
    memberMission3 =
        MemberMission.builder()
            .member(testMember)
            .mission(mission3)
            .status(MissionStatus.PROGRESS)
            .build();
    memberMissionRepository.saveAll(List.of(memberMission1, memberMission2, memberMission3));
  }

  @Test
  @DisplayName("강남역 기준 3km 반경 내의 미션 조회")
  void findMissionsWithin3KmFromGangnam() {
    // given
    Float latitude = 37.498095f; // 강남역 위도
    Float longitude = 127.027610f; // 강남역 경도
    Double radius = 3.0; // 3km
    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id").ascending());

    // when
    Page<Mission> missions =
        missionService.findAvailableMissionsByLocation(latitude, longitude, radius, pageRequest);

    // then
    assertThat(missions.getContent()).hasSize(2); // 강남역, 역삼역 미션만 포함
    assertThat(missions.getContent())
        .extracting("description")
        .containsExactlyInAnyOrder("강남역 미션", "역삼역 미션");
  }

  @Test
  @DisplayName("삭제된 미션은 조회되지 않음")
  void deletedMissionsShouldNotBeReturned() {
    // given
    Float latitude = 37.498095f;
    Float longitude = 127.027610f;
    Double radius = 10.0;
    PageRequest pageRequest = PageRequest.of(0, 10);

    // when
    Page<Mission> missions =
        missionService.findAvailableMissionsByLocation(latitude, longitude, radius, pageRequest);

    // then
    assertThat(missions.getContent()).extracting("description").doesNotContain("삭제된 미션");
  }

  @Test
  @DisplayName("페이징 처리 확인")
  void paginationShouldWorkCorrectly() {
    // given
    Float latitude = 37.498095f;
    Float longitude = 127.027610f;
    Double radius = 20.0; // 모든 미션이 포함되는 반경
    PageRequest pageRequest = PageRequest.of(0, 2); // 페이지당 2개

    // when
    Page<Mission> missions =
        missionService.findAvailableMissionsByLocation(latitude, longitude, radius, pageRequest);

    // then
    assertThat(missions.getContent()).hasSize(2); // 페이지 크기만큼만 반환
    assertThat(missions.getTotalElements()).isEqualTo(3); // 전체 미션 수는 3개
    assertThat(missions.getTotalPages()).isEqualTo(2); // 총 2페이지
  }

  @Test
  @DisplayName("진행중인 미션 목록 조회 - 페이징 처리 확인")
  void getProgressMissions() {
    // given
    PageRequest pageRequest = PageRequest.of(0, 1); // 페이지당 1개씩만 조회

    // when
    Page<MissionResponseDto.MemberMissionResponseDto> progressMissions =
        missionService.getMemberMissions(testMember.getId(), MissionStatus.PROGRESS, pageRequest);

    // then
    assertThat(progressMissions.getContent()).hasSize(1); // 페이지 크기만큼만 조회
    assertThat(progressMissions.getTotalElements()).isEqualTo(2); // 전체 진행중인 미션 수는 2개
    assertThat(progressMissions.getTotalPages()).isEqualTo(2); // 총 2페이지
    assertThat(progressMissions.getNumber()).isEqualTo(0); // 현재 페이지 번호
    assertThat(progressMissions.hasNext()).isTrue(); // 다음 페이지 존재

    // 두 번째 페이지 조회
    PageRequest nextPageRequest = PageRequest.of(1, 1);
    Page<MissionResponseDto.MemberMissionResponseDto> nextProgressMissions =
        missionService.getMemberMissions(
            testMember.getId(), MissionStatus.PROGRESS, nextPageRequest);

    assertThat(nextProgressMissions.getContent()).hasSize(1);
    assertThat(nextProgressMissions.getNumber()).isEqualTo(1);
    assertThat(nextProgressMissions.hasNext()).isFalse();
  }

  @Test
  @DisplayName("완료된 미션 목록 조회")
  void getCompletedMissions() {
    // given
    PageRequest pageRequest = PageRequest.of(0, 10);

    // when
    Page<MissionResponseDto.MemberMissionResponseDto> completedMissions =
        missionService.getMemberMissions(testMember.getId(), MissionStatus.COMPLETE, pageRequest);

    // then
    assertThat(completedMissions.getContent()).hasSize(1);
    assertThat(completedMissions.getContent().get(0).getDescription()).isEqualTo("역삼역 미션");
    assertThat(completedMissions.getContent().get(0).getStatus()).isEqualTo(MissionStatus.COMPLETE);
    assertThat(completedMissions.getContent().get(0).getCompletedAt()).isNotNull();
    assertThat(completedMissions.getTotalPages()).isEqualTo(1); // 총 1페이지
    assertThat(completedMissions.hasNext()).isFalse(); // 다음 페이지 없음
  }
}
