package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.member.converter.MemberConverter;
import umc.spring.domain.member.converter.MemberPreferConverter;
import umc.spring.domain.member.data.FoodCategory;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.mapping.MemberPrefer;
import umc.spring.domain.member.repository.foodCategory.FoodCategoryRepository;
import umc.spring.domain.member.repository.member.MemberRepository;
import umc.spring.domain.member.web.dto.MemberRequestDTO;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.FoodCategoryHandler;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

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
}
