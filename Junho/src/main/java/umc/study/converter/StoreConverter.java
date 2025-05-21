package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Store;
import umc.study.domain.mapping.Review;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {
    // store엔티티를 응답dto 객체로 변환
    public static StoreResponseDTO.AddResultDTO toAddResultDTO(Store store){
        return StoreResponseDTO.AddResultDTO.builder()
                .id(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    // 요청dto 객체로 store엔티티 생성
    public static Store toStore(StoreRequestDTO.AddDto request, String region) {
        return Store.builder()
                .name(request.getName())
                .storeCategory(request.getStoreCategory())
                .address(request.getAddress())
                .region(region)
                .score((float)0.0)
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review){
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .nickname(review.getUser().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .content(review.getContent())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream().map(StoreConverter::toReviewPreviewDTO).collect(Collectors.toList());
        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isFirst(reviewList.isFirst())
                .isLast(reviewList.isLast())
                .totalElements(reviewList.getTotalElements())
                .totalPages(reviewList.getTotalPages())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }


}
