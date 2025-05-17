package umc.spring.global.common.converter;

import org.springframework.data.domain.Page;
import umc.spring.global.common.dto.PageDTO;

public class PageConverter {

    public static PageDTO.ListPageDto pageToListPageDto(Page<?> page) {
        return PageDTO.ListPageDto.builder()
                .listSize(page.getSize())
                .totalPage(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .isFirst(page.isFirst())
                .isLast(page.isLast())
                .build();
    }

}
