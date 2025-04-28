package umc.spring.domain.member.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.service.MemberCommandService;
import umc.spring.domain.member.service.MemberQueryService;
import umc.spring.domain.member.web.dto.MemberResponseDTO;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @DeleteMapping("/")
    @ResponseBody
    public void deleteMember() {
        memberCommandService.deleteMember();
    }

    @PostMapping("/store")
    public String scrapStore(@RequestParam Long storeId) {
        memberCommandService.scrapStore(storeId);
        return "scrap";
    }

    @GetMapping("/{id}") // 원래는 Request Header에서 유저 정보 가져옴
    public ResponseEntity<MemberResponseDTO.MyPageDTO> myPageInfo(@PathVariable("id") Long memberId) {
        MemberResponseDTO.MyPageDTO memberDTO = memberQueryService.findMemberInfo(memberId);
        return ResponseEntity.ok(memberDTO);
    }

}
