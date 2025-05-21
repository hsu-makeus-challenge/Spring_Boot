package umc.spring.domain.temp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface TempQueryService {
    void checkFlag(Integer flag);
    Slice<TempQueryServiceImpl.MemberMissionDto> findAllSlice();
    Page<TempQueryServiceImpl.MemberMissionDto> findAllPage();
}
