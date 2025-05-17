package umc.spring.domain.store.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.review.data.Review;
import umc.spring.domain.store.data.Store;
import umc.spring.domain.store.web.dto.StoreRequestDTO;
import umc.spring.domain.store.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.addStoreDto request) {
        return Store.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .address(request.getAddress())
                .score(request.getScore())
                .build();
    }

    public static StoreResponseDTO.addResultDto toResultDto(Store store) {
        return StoreResponseDTO.addResultDto.builder()
                .storeId(store.getId())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewDto reviewPreViewDto(Review review) {
        return StoreResponseDTO.ReviewPreViewDto.builder()
                .ownerNickName(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }

    public static StoreResponseDTO.ReviewPreViewListDto reviewPreViewListDto(Page<Review> reviewList) {

        List<StoreResponseDTO.ReviewPreViewDto> reviewPreViewDtoList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDto).toList();

        return StoreResponseDTO.ReviewPreViewListDto.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDtoList.size())
                .reviewList(reviewPreViewDtoList)
                .build();
    }

}
