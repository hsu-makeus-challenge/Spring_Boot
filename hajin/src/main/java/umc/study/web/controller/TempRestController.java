package umc.study.web.controller;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.TempConverter;
import umc.study.service.TempService.TempQueryService;
import umc.study.web.dto.TempResponse;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
@Slf4j
public class TempRestController {
    private final TempQueryService tempQueryService;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testAPI(){
        return ApiResponse.onSuccess(TempConverter.toTempDTO());
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionAPI(@RequestParam Integer flag){
        log.info("exception API 호출됨 - flag: {}", flag);
        tempQueryService.CheckFlag(flag);
        log.info("/exception 정상 처리 완료");
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
        //log.info("여기는 안찍힌다는 거지?");
    }
}
