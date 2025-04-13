package umc.spring.domain.member.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.member.service.MemberTestCommandService;

@RestController
@RequestMapping("/test/members")
@RequiredArgsConstructor
public class MemberTestController {

    private final MemberTestCommandService memberTestCommandService;

    @PostMapping("/")
    public void addMember() {
        memberTestCommandService.addTestMember();
    }

}
