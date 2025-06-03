package umc.spring.domain.member.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import umc.spring.domain.member.service.LoginService;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/oauth/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        log.info("hello1");
        loginService.socialLogin(code, registrationId);
    }

}
