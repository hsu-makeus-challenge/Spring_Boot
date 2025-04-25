package umc.spring.domain.member.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import umc.spring.domain.member.service.MemberTestCommandService;

@Controller
@RequestMapping("/test/members")
@RequiredArgsConstructor
public class MemberTestController {

    private final MemberTestCommandService memberTestCommandService;

    @PostMapping("/")
    public void addMember() {
        memberTestCommandService.addTestMember();
    }

    @GetMapping("/store")
    public String storeScrapPage() {
        return "scrap";
    }

}
