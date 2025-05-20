package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.store.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.AddStoreResultDto toAddStoreResultDto(Store store) {
        return StoreResponseDTO.AddStoreResultDto.builder()
                .storeId(store.getStoreId())
                .regionId(store.getRegion().getRegionId())
                .category(store.getCategory())
                .address(store.getAddress())
                .score(store.getScore())
                .openStatus(store.getOpenStatus())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.AddRegionResultDto addRegionResultDto(Store store) {
        return StoreResponseDTO.AddRegionResultDto.builder()
                .storeId(store.getStoreId())
                .regionId(store.getRegion().getRegionId())
                .build();
    }

    // 리뷰 조회
    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickName(review.getUser().getName())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .content(review.getContent())
                .build();
    }

    // 리뷰 목록 조회
    public static StoreResponseDTO.ReviewPreViewListDto reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList  = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());


        return StoreResponseDTO.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
