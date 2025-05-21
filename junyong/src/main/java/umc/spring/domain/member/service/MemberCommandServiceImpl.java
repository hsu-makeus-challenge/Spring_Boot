package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.domain.member.web.dto.MemberResponseDTO;
import umc.spring.domain.mission.data.enums.MissionStatus;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;
import umc.spring.global.common.apiPayload.exception.handler.FoodCategoryHandler;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {
        Member member = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).toList();

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach( memberPrefer -> memberPrefer.setMember(member));

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

}
