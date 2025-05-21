package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Review;
import umc.spring.domain.ReviewImage;
import umc.spring.domain.User;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.UserMission;
import umc.spring.web.dto.UserDTO.UserRequestDTO;
import umc.spring.web.dto.UserDTO.UserResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user){
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }

        return User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .gender(gender)
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .birthYear(request.getBirthYear())
                .birthMonth(request.getBirthMonth())
                .birthDay(request.getBirthDay())
                .userFoodCategoryList(new ArrayList<>())
                .build();
    }

    public static UserResponseDTO.UserReviewPreViewDTO reviewPreViewDTO(Review review){
        return UserResponseDTO.UserReviewPreViewDTO.builder()
                .ownerNickname(review.getUser().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getContent())
                .imageUrls(review.getReviewImageList().stream()
                        .map(ReviewImage::getImageUrl)
                        .collect(Collectors.toList())
                )
                .build()
                ;
    }
    public static UserResponseDTO.UserReviewPreViewListDTO userReviewPreViewListDTO(Page<Review> reviewList){
        List<UserResponseDTO.UserReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(UserConverter::reviewPreViewDTO)
                .collect(Collectors.toList())
                ;

        return UserResponseDTO.UserReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build()
                ;
    }


    public static UserResponseDTO.UserMissionPreViewDTO userMissionPreViewDTO(UserMission userMission){
        return UserResponseDTO.UserMissionPreViewDTO.builder()
                .userMissionId(userMission.getId())
                .storeName(userMission.getStoreMission().getStore().getName())
                .content(userMission.getStoreMission().getMission().getContent())
                .reward(userMission.getStoreMission().getMission().getReward())
                .userMissionStatus(userMission.getStatus())
                .deadline(userMission.getStoreMission().getDeadline().toLocalDate())
                .createdAt(userMission.getCreatedAt().toLocalDate())
                .build()
                ;
    }
    public static UserResponseDTO.UserMissionPreViewListDTO userMissionPreViewListDTO(Page<UserMission> userMissionList){
        List<UserResponseDTO.UserMissionPreViewDTO> userMissionPreViewDTOList = userMissionList.stream()
                .map(UserConverter::userMissionPreViewDTO)
                .toList()
                ;

        return UserResponseDTO.UserMissionPreViewListDTO.builder()
                .isLast(userMissionList.isLast())
                .isFirst(userMissionList.isFirst())
                .totalPage(userMissionList.getTotalPages())
                .totalElements(userMissionList.getTotalElements())
                .listSize(userMissionPreViewDTOList.size())
                .userMissionList(userMissionPreViewDTOList)
                .build()
                ;
    }
}
