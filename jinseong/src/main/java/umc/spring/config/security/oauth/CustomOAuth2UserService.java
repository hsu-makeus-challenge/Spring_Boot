package umc.spring.config.security.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.OAuth;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.Provider;
import umc.spring.domain.enums.Role;
import umc.spring.domain.enums.UserStatus;
import umc.spring.repository.OAuthRepository;
import umc.spring.repository.UserRepository.UserRepository;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuthRepository oAuthRepository;
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(request);
        Map<String, Object> attributes = oAuth2User.getAttributes();

//        System.out.println("Google OAuth2 사용자 정보:");
//        attributes.forEach((key, value) -> System.out.println(key + ": " + value));

        String socialId = (String) attributes.get("sub"); // Google 고유 ID
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        log.info("socialId: " + socialId);
        log.info("email: " + email);
        log.info("name: " + name);

        if (socialId == null || email == null) {
            throw new OAuth2AuthenticationException("Required OAuth2 attributes are missing");
        }

        Provider provider = Provider.GOOGLE;

        // 1. OAuth 정보로 기존 유저 조회
        OAuth oAuth = oAuthRepository.findBySocialIdAndProvider(socialId, provider).orElse(null);
        System.out.println("== OAuth 결과 == " + oAuth);

        User user;
        if (oAuth == null) {
            // 2. 없다면, 회원가입
            user = userRepository.save(User.builder()
                    .email(email)
                    .name(name)
                    .password("")
                    .role(Role.USER)
                    .gender(Gender.MALE)
                    .verified(true)
                    .address("")
                    .detailAddress("")
                    .birthYear(1900)
                    .birthMonth(1)
                    .birthDay(1)
                    .status(UserStatus.ACTIVE)
                    .build());

            oAuthRepository.save(OAuth.builder()
                    .socialId(socialId)
                    .provider(provider)
                    .user(user)
                    .build());
        }
        // 있다면, 사용자 정보 불러오기
        else {
            user = oAuth.getUser();
        }

        // 3. 로그인 처리를 위해 인증객체 반환
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())),
                attributes,
                "sub"
        );
    }
}
