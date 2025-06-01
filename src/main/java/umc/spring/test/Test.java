package umc.spring.test;

import umc.spring.domain.Region;
import umc.spring.domain.enums.TandF;
import umc.spring.domain.User;


public class Test {

    public static Region DummyRegion() {
        return Region.builder()
                .regionName("서울특별시 성북구")
                .build();
    }

    public static User DummyUser() {

        return User.builder()
                .name("테스트 유저")
                .region(Test.DummyRegion()) // 시/도/구: 코드 기반 Region 객체
                .regionDetail("삼선교로 16길 116(삼선동2가)") // 상세 주소
                .email("testuser@example.com")
                .phoneNumber("010-1111-2222")
                .gender(User.Gender.M)
                .password("test-password")
                .marketingConsent(TandF.T)
                .LocationConsent(TandF.T)
                .status(User.Active.Active)
                .newEventAlarm(TandF.T)
                .reviewRelyAlarm(TandF.T)
                .inquriyRelyAlarm(TandF.T)
                .build();
    }
}
