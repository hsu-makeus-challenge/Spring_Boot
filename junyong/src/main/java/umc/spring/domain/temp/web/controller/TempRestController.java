package umc.spring.domain.temp.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.temp.converter.TempConverter;
import umc.spring.domain.temp.service.TempQueryService;
import umc.spring.domain.temp.service.TempQueryServiceImpl;
import umc.spring.domain.temp.web.dto.TempResponse;
import umc.spring.global.common.apiPayload.ApiResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag) {
        tempQueryService.checkFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }

    @GetMapping("/reviews/page")
    public ApiResponse<Page<TempQueryServiceImpl.MemberMissionDto>> missionsPage() {
        Page<TempQueryServiceImpl.MemberMissionDto> MemberMissionList = tempQueryService.findAllPage();
        return ApiResponse.onSuccess(MemberMissionList);
    }

    @GetMapping("/reviews/slice")
    public ApiResponse<Slice<TempQueryServiceImpl.MemberMissionDto>> missionsSlice() {
        Slice<TempQueryServiceImpl.MemberMissionDto> MemberMissionList = tempQueryService.findAllSlice();
        return ApiResponse.onSuccess(MemberMissionList);
    }


}
