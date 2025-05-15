package umc.spring.converter;

import umc.spring.domain.OAuth;
import umc.spring.domain.User;
import umc.spring.domain.enums.SocialType;

public class OAuthConverter {
    public static OAuth toOAuth(User user, SocialType socialType) {
        return OAuth.builder()
                .socialType(socialType)
                .user(user)
                .build();
    }
}
