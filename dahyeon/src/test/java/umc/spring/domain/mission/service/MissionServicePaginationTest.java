package umc.spring.domain.mission.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.enums.FoodCategory;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.repository.MemberRepository;
import umc.spring.domain.mission.entity.Mission;
import umc.spring.domain.mission.entity.enums.MissionStatus;
import umc.spring.domain.mission.entity.mappings.MemberMission;
import umc.spring.domain.mission.repository.MemberMissionRepository;
import umc.spring.domain.mission.repository.MissionRepository;
import umc.spring.domain.store.entity.Store;
import umc.spring.domain.store.repository.StoreRepository;

@SpringBootTest
@Transactional
@DisplayName("MissionService 내 미션 상태 기반 조회 및 정렬 테스트")
class MissionServicePaginationTest {

  @Autowired private MissionServiceImpl missionService;
  @Autowired private MissionRepository missionRepository;
  @Autowired private MemberRepository memberRepository;
  @Autowired private StoreRepository storeRepository;
  @Autowired private MemberMissionRepository memberMissionRepository;

  private Long testMemberId;
  private Store testStore;

  @BeforeEach
  void setUp() {
    testStore =
        storeRepository.save(
            Store.builder()
                .name("강남 맛집")
                .score(4.5f)
                .openTime(LocalDate.now())
                .closeTime(LocalDate.now().plusDays(1))
                .address("서울시 강남구")
                .latitude(37.498095f)
                .longitude(127.027610f)
                .category(FoodCategory.KOREAN)
                .build());

    Member member =
        memberRepository.save(
            Member.builder()
                .nickname("테스트닉네임")
                .email("test@example.com")
                .name("테스트")
                .birth(LocalDate.of(1999, 3, 15))
                .gender(Gender.FE)
                .status(MemberStatus.ACTIVE)
                .build());

    testMemberId = member.getId();

    IntStream.rangeClosed(1, 5)
        .forEach(
            i -> {
              Mission mission =
                  Mission.builder()
                      .description("진행중 미션 " + i)
                      .rewardPoint(100)
                      .minAmount(10000)
                      .store(testStore)
                      .build();
              mission = missionRepository.save(mission);

              MemberMission mm =
                  MemberMission.builder()
                      .mission(mission)
                      .member(member)
                      .status(MissionStatus.PROGRESS)
                      .build();

              memberMissionRepository.save(mm);
            });

    IntStream.rangeClosed(6, 10)
        .forEach(
            i -> {
              Mission mission =
                  Mission.builder()
                      .description("완료된 미션 " + i)
                      .rewardPoint(200)
                      .minAmount(15000)
                      .store(testStore)
                      .build();
              mission = missionRepository.save(mission);

              MemberMission mm =
                  MemberMission.builder()
                      .mission(mission)
                      .member(member)
                      .status(MissionStatus.COMPLETE)
                      .completedAt(LocalDate.now())
                      .build();

              memberMissionRepository.save(mm);
            });
  }

  @Test
  @DisplayName("IN_PROGRESS 상태의 미션만 조회된다")
  void testGetInProgressMissions() {
    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 0, MissionStatus.PROGRESS);

    assertEquals(5, page.getTotalElements());
    assertTrue(page.getContent().stream().allMatch(mm -> mm.getStatus() == MissionStatus.PROGRESS));
  }

  @Test
  @DisplayName("COMPLETE 상태의 미션만 조회된다")
  void testGetCompletedMissions() {
    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 0, MissionStatus.COMPLETE);

    assertEquals(5, page.getTotalElements());
    assertTrue(page.getContent().stream().allMatch(mm -> mm.getStatus() == MissionStatus.COMPLETE));
  }

  @Test
  @DisplayName("미션 목록은 createdDate 기준으로 내림차순 정렬되어야 한다")
  void testMissionOrderByCreatedDateDesc() {
    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 0, MissionStatus.PROGRESS);
    List<MemberMission> missions = page.getContent();

    for (int i = 1; i < missions.size(); i++) {
      LocalDateTime before = missions.get(i - 1).getCreatedDate();
      LocalDateTime after = missions.get(i).getCreatedDate();
      assertTrue(before.isAfter(after) || before.isEqual(after));
    }
  }

  @Test
  @DisplayName("존재하지 않는 페이지를 요청하면 빈 결과가 반환된다")
  void testOutOfRangePageReturnsEmpty() {
    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 10, MissionStatus.PROGRESS);
    assertEquals(0, page.getContent().size());
  }

  @Test
  @DisplayName("미션은 createdDate 기준으로 내림차순 정렬되고, description도 그 순서와 일치해야 한다")
  void testMissionDescriptionMatchesCreatedDateDesc() {
    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 0, MissionStatus.COMPLETE);
    List<MemberMission> missions = page.getContent();

    for (int i = 0; i < missions.size(); i++) {
      String expected = "완료된 미션 " + (10 - i);
      String actual = missions.get(i).getMission().getDescription();
      assertEquals(expected, actual, "description 순서가 최신 createdDate 순서와 일치해야 함");
    }
  }

  @Test
  @DisplayName("가장 최근에 저장된 미션이 목록의 첫 번째에 있어야 한다")
  void testLatestMissionIsFirst() {
    Mission latest =
        Mission.builder()
            .description("가장 최신 미션")
            .rewardPoint(999)
            .minAmount(9999)
            .store(testStore)
            .build();
    latest = missionRepository.save(latest);

    Member member = memberRepository.findById(testMemberId).orElseThrow();
    MemberMission mm =
        MemberMission.builder()
            .mission(latest)
            .member(member)
            .status(MissionStatus.PROGRESS)
            .build();
    memberMissionRepository.save(mm);

    Page<MemberMission> page =
        missionService.getMyMissions(testMemberId, 0, MissionStatus.PROGRESS);
    MemberMission first = page.getContent().get(0);

    assertEquals("가장 최신 미션", first.getMission().getDescription());
  }
}
