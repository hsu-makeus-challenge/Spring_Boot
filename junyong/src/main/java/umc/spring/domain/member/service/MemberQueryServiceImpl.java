package umc.spring.domain.member.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.converter.MemberMissionConverter;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;


@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public MemberResponseDTO.MissionListDto getMissions(MissionStatus status, Integer page) {

        Member member = memberRepository.findById(1L).get();
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> memberMissionList = memberMissionRepository.findByMemberAndStatus(member, status, pageRequest);
        if(page >= memberMissionList.getTotalPages() && memberMissionList.getTotalElements() != 0) {
            throw new ErrorHandler(ErrorStatus.PAGE_OUT_OF_RANGE);
        }
        return MemberMissionConverter.toMissionListDto(memberMissionList);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDto getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new ErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDto(member);
    }
}
