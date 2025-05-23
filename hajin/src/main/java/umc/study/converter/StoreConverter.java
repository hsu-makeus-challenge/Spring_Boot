package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static ReviewResponseDTO.ReviewDetailDTO reviewDetailDTO(Review review){
        return ReviewResponseDTO.ReviewDetailDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .build();
    }
    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<ReviewResponseDTO.ReviewDetailDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewDetailDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}