package umc.spring.domain.temp.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.domain.member.data.PointHistory;
import umc.spring.domain.member.repository.pointHistory.PointHistoryRepository;
import umc.spring.domain.temp.converter.TempConverter;
import umc.spring.domain.temp.repository.PointHistoryJdbcRepository;
import umc.spring.domain.temp.service.TempQueryService;
import umc.spring.domain.temp.service.TempQueryServiceImpl;
import umc.spring.domain.temp.web.dto.TempResponse;
import umc.spring.global.common.apiPayload.ApiResponse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
@Slf4j
public class TempRestController {

    private final TempQueryService tempQueryService;
    private final PointHistoryRepository pointHistoryRepository;
    private final PointHistoryJdbcRepository pointHistoryJdbcRepository;

    int sum = 0;

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

    @GetMapping("/forStreamTest")
    public void userFor(){

        long start, end;
        List<PointHistory> all = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            all.addAll(pointHistoryJdbcRepository.findAll());
        }

        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        log.info("size = {}", all.size());

        // 일반 for 문
        sum = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < all.size(); i++) {
//            addPoint(i);
            addPoint(all.get(i).getPoint());
        }
        end = System.currentTimeMillis();
        log.info("for 소요시간 = {}초", (end-start)/1000.0);

        //for - each 문
        sum = 0;
        start = System.currentTimeMillis();
//        for (int i : arr) {
//            addPoint(i);
//        }
        for (PointHistory pointHistory : all) {
            addPoint(pointHistory.getPoint());
        }
        end = System.currentTimeMillis();
        log.info("for 소요시간 = {}초", (end-start)/1000.0);

        //stream 부분
        sum = 0;
        start = System.currentTimeMillis();
        all.stream()
                .forEach(pointHistory -> addPoint(pointHistory.getPoint()));
//        IntStream.of(arr).forEach(this::addPoint);
        end = System.currentTimeMillis();
        log.info("stream 소요시간 = {}초", (end-start)/1000.0);

    }

    private void addPoint(int num) {
        sum += num;
    }


}
