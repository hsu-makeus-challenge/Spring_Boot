package umc.study.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    public static class AddDto{
        private String nickname;
        private float score;
        private String content;
        private String image;
        private long storeId;
    }
}
