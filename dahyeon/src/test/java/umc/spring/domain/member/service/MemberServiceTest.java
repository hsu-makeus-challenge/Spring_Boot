package umc.spring.domain.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import umc.spring.domain.member.dto.MemberResponseDto;
import umc.spring.domain.member.entity.Member;
import umc.spring.domain.member.entity.PointLog;
import umc.spring.domain.member.entity.enums.Gender;
import umc.spring.domain.member.entity.enums.MemberStatus;
import umc.spring.domain.member.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired private MemberService memberService;

  @Autowired private MemberRepository memberRepository;

  private Member testMember;

  @BeforeEach
  void setUp() {
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

    PointLog pointLog1 = PointLog.builder().point(1000).build();
    PointLog pointLog2 = PointLog.builder().point(2000).build();

    testMember.addPointLog(pointLog1);
    testMember.addPointLog(pointLog2);
  }

  @Test
  @DisplayName("마이페이지 조회 성공")
  void getMyPage_Success() {
    // when
    MemberResponseDto.MyPageResponseDto myPage = memberService.getMyPage(testMember.getId());

    // then
    assertThat(myPage.getNickname()).isEqualTo("테스트닉네임");
    assertThat(myPage.getEmail()).isEqualTo("test@example.com");
    assertThat(myPage.getTotalPoint()).isEqualTo(3000);
  }
  // TODO : 존재하지 않는 회원의 마이페이지 조회 실패

}
