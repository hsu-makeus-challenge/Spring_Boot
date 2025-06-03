package umc.spring.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    public void socialLogin(String code, String registrationId) {
        System.out.println("code = " + code);
        System.out.println("registrationId = " + registrationId);
    }

}
