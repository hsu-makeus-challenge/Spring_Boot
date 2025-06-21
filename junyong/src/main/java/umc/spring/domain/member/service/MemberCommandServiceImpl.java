package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.converter.LoginConverter;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.converter.MemberMissionConverter;
import umc.spring.domain.member.converter.MemberPreferConverter;
import umc.spring.domain.member.data.FoodCategory;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.data.mapping.MemberPrefer;
import umc.spring.domain.member.repository.foodCategory.FoodCategoryRepository;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.member.web.dto.LoginDto;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;
import umc.spring.global.common.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member member = MemberConverter.toMember(request);
        member.encodePassword(passwordEncoder.encode(request.getPassword()));
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(Long.parseLong(category)).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach( memberPrefer -> memberPrefer.setMember(member));

        log.info("member save");
        return memberRepository.save(member); // 멤버 반환
    }

    @Override
    @Transactional
    public MemberResponseDTO.CompleteDto completeMission(Long memberMissionId, String confirmNumber) {

        Member member = memberRepository.findById(1L).get();
        MemberMission memberMission = memberMissionRepository.findByIdAndMember(memberMissionId, member);

        // 예외처리
        if (!memberMission.getConfirmNumber().equals(confirmNumber)) { // 확인 번호가 틀렸을 때
            throw new ErrorHandler(ErrorStatus.MISSION_INVALID_CONFIRM_NUMBER);
        }else if (memberMission.getMission().getDeadline().isBefore(LocalDate.now())) { // 마감기한이 지났을 때
            throw new ErrorHandler(ErrorStatus.MISSION_EXPIRED);
        } else if (memberMission.getStatus() == MissionStatus.COMPLETE) { // 이미 성공했을 떄
            throw new ErrorHandler(ErrorStatus.MISSION_COMPLETED_MISSION);
        } else if (memberMission.getStatus() == MissionStatus.FAILED) { // 도전했는데 실패했을 때
            throw new ErrorHandler(ErrorStatus.MISSION_FAILED_MISSION);
        }

        try {
            memberMission.changeStatus(MissionStatus.COMPLETE);
        }catch (Exception e) {
            throw new RuntimeException("이건 뭐 안타깝게 된거죠...");
        }
        return MemberMissionConverter.toCompleteDto(memberMission);
    }

    @Override
    public LoginDto.LoginResultDto loginMember(LoginDto.LoginRequestDto request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new ErrorHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authenticationToken);

        return LoginConverter.toLoginResultDto(
                member.getId(),
                accessToken
        );
    }

}
