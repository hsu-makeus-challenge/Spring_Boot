package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.mapping.StoreMission;
import umc.spring.web.dto.StoreDTO.StoreRequestDTO;
import umc.spring.web.dto.StoreDTO.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

    public class StoreConverter {
        public static StoreResponseDTO.CreateStoreResultDTO toCreateStoreResultDTO(Store store){
            return StoreResponseDTO.CreateStoreResultDTO.builder()
                    .storeId(store.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
        }

        public static Store toStore(StoreRequestDTO.CreateStoreDto request){
            return Store.builder()
                    .name(request.getName())
                    .address(request.getAddress())
                    .storeHoursList(new ArrayList<>())
                    .storeFoodCategoryList(new ArrayList<>())
                    .build();
        }


        public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
            return StoreResponseDTO.ReviewPreViewDTO.builder()
                    .ownerNickname(review.getUser().getName())
                    .score(review.getScore())
                    .createdAt(review.getCreatedAt().toLocalDate())
                    .body(review.getContent())
                    .build();
        }
        public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){
            List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                    .map(StoreConverter::reviewPreViewDTO)
                    .collect(Collectors.toList())
                    ;

            return StoreResponseDTO.ReviewPreViewListDTO.builder()
                    .isLast(reviewList.isLast())
                    .isFirst(reviewList.isFirst())
                    .totalPage(reviewList.getTotalPages())
                    .totalElements(reviewList.getTotalElements())
                    .listSize(reviewPreViewDTOList.size())
                    .reviewList(reviewPreViewDTOList)
                    .build();
        }


        public static StoreResponseDTO.StoreMissionPreViewDTO storeMissionPreViewDTO(StoreMission storeMission){
            return StoreResponseDTO.StoreMissionPreViewDTO.builder()
                    .storeMissionId(storeMission.getId())
                    .content(storeMission.getMission().getContent())
                    .reward(storeMission.getMission().getReward())
                    .deadline(storeMission.getDeadline().toLocalDate())
                    .createdAt(storeMission.getCreatedAt().toLocalDate())
                    .build()
                    ;
        }
        public static StoreResponseDTO.StoreMissionPreViewListDTO storeMissionPreViewListDTO(Page<StoreMission> storeMissionList){
            List<StoreResponseDTO.StoreMissionPreViewDTO> storeMissionPreViewDTOList = storeMissionList.stream()
                    .map(StoreConverter::storeMissionPreViewDTO)
                    .toList()
                    ;

            return StoreResponseDTO.StoreMissionPreViewListDTO.builder()
                    .isLast(storeMissionList.isLast())
                    .isFirst(storeMissionList.isFirst())
                    .totalPage(storeMissionList.getTotalPages())
                    .totalElements(storeMissionList.getTotalElements())
                    .listSize(storeMissionPreViewDTOList.size())
                    .storeMissionList(storeMissionPreViewDTOList)
                    .build()
                    ;
        }
    }
