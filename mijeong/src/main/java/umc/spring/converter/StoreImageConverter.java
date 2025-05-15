package umc.spring.converter;

import umc.spring.domain.StoreImage;

import java.util.List;
import java.util.stream.Collectors;

public class StoreImageConverter {

    // createDto -> StoreImage Entity
    public static List<StoreImage> toStoreImageList(List<String> storeImageList) {
        return storeImageList.stream()
                .map(storeImage ->
                        StoreImage.builder()
                                .storeImageUrl(storeImage)
                                .build()
                ).collect(Collectors.toList());
    }
}
