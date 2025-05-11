package umc.spring.domain.store.web.dto;

import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class addStoreDto {
        String name;
        Long categoryId;
        String address;
        Float score;
    }

}
