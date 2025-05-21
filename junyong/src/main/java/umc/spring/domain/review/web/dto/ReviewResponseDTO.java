package umc.spring.domain.review.web.dto;

import lombok.Builder;
import lombok.Getter;
import umc.spring.global.common.dto.PageDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    public static class addResultDto{
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class ReviewListDto{
        List<ReviewDto> reviewList;
        PageDTO.ListPageDto pageInfo;
    }

    @Builder
    @Getter
    public static class ReviewDto {
        String memberName;
        String content;
        Float score;
        LocalDate createdAt;
    }

}
