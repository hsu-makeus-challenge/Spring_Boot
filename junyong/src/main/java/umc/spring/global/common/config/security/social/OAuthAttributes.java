package umc.spring.global.common.config.security.social;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import umc.spring.domain.member.data.Member;
import umc.spring.domain.member.data.enums.Gender;
import umc.spring.domain.member.data.enums.Role;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String pictureURL;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey,
                           String name, String email, String pictureURL) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.pictureURL = pictureURL;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        switch (registrationId) {
            case "google":
                ofGoogle(userNameAttributeName, attributes);
                break;
            case "kakao":
                break;
            default:
                throw new ErrorHandler(ErrorStatus.INVALID_REGISTRATION_ID);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .pictureURL((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .role(Role.USER)
                .address("")
                .specAddress("")
                .gender(Gender.NONE)
                .birth("")
                .phone("")
                .locationAgree(false)
                .marketingAgree(false)
                .password("")
                .memberPreferList(new ArrayList<>())
                .eventNoticeList(new ArrayList<>())
                .inquiryNoticeList(new ArrayList<>())
                .reviewNoticeList(new ArrayList<>())
                .memberMissionList(new ArrayList<>())
                .build();
    }

}
