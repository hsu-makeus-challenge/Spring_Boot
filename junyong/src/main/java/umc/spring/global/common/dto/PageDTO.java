package umc.spring.global.common.dto;

import lombok.Builder;
import lombok.Getter;

public class PageDTO {

    @Builder
    @Getter
    public static class ListPageDto {
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

}
