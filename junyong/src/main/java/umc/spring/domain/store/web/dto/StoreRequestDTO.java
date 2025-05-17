package umc.spring.domain.store.web.dto;

import lombok.Getter;
import umc.spring.domain.store.data.StoreCategory;

public class StoreRequestDTO {

    @Getter
    public static class addStoreDto {
        String name;
        StoreCategory category;
        String address;
        Float score;
    }

}
