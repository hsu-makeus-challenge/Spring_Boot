package umc.spring.domain.member.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.service.MemberCommandService;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

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

}
