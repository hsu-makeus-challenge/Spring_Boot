package umc.spring.converter;

import umc.spring.domain.StoreHours;
import umc.spring.domain.enums.Weekday;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StoreHoursConverter {

    public static List<StoreHours> toStoreHours(List<String> open, List<String> close) {

        List<StoreHours> storeHoursList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            // 요일에 해당하는 Weekday enum 값
            Weekday weekday = Weekday.values()[i];

            // open, close 시간을 LocalTime으로 변환
            LocalTime openTime = LocalTime.parse(open.get(i));
            LocalTime closeTime = LocalTime.parse(close.get(i));

            // StoreHours 생성
            StoreHours storeHours = StoreHours.builder()
                    .day(weekday)
                    .openTime(openTime)
                    .closeTime(closeTime)
                    .build();

            // 생성된 StoreHours 객체를 리스트에 추가
            storeHoursList.add(storeHours);
        }

        return storeHoursList;
    }
}
