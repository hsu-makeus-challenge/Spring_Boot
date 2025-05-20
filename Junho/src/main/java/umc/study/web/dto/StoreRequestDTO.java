package umc.study.web.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class AddDto{
        private String name;
        private String storeCategory;
        private String address;
    }
}
