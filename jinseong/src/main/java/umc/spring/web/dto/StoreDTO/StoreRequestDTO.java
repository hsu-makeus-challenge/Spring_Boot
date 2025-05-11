package umc.spring.web.dto.StoreDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import java.util.List;

public class StoreRequestDTO {
    @Getter
    public static class CreateStoreDto{
        @NotBlank
        String name;

        @Size(min = 5, max = 12)
        String address;

        @NotNull
        Long neighborhood;

        @ExistCategories
        List<Long> foodCategory;

        @Size(min = 7, max = 7)
        List<@Pattern(regexp = "^\\d{2}:\\d{2}$") String> openHours;

        @Size(min = 7, max = 7)
        List<@Pattern(regexp = "^\\d{2}:\\d{2}$") String> closeHours;
    }
}
