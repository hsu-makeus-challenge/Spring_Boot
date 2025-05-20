package umc.study.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    @Setter
    public static class MissionDto{
        @NotBlank(message = "Title cannot be blank")
        private String title;

        @NotBlank(message = "Body cannot be blank")
        private String body;

        @NotNull(message = "Reward points cannot be null")
        private Integer rewardPoint;

//        @NotNull(message = "End date cannot be null")
//        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
//        private LocalDateTime startDate;

//        @NotNull(message = "Start date cannot be null")
//        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
//        private LocalDateTime endDate;
    }
}
