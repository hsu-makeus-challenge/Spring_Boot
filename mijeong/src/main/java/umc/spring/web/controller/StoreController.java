package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistRegion;
import umc.spring.web.dto.store.StoreRequest;
import umc.spring.web.dto.store.StoreResponse;


@Tag(name = "가게 페이지", description = "가게에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreCommandService storeCommandService;

    @Operation(
            summary = "지역에 가게 추가",
            description = "특정 지역에 가게를 추가하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER4001", description = "아이디와 일치하는 사용자가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REGION4001", description = "아이디와 일치하는 지역이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "regionId", description = "가게를 등록하려는 지역의 아이디", example = "1", required = true),
    })
    @PostMapping()
    public ApiResponse<StoreResponse.StoreCreateResultDto> postStoreByRegion(@RequestParam(name="regionId") @ExistRegion Long regionId,
                                                        @RequestBody @Valid StoreRequest.StoreCreateDto request){
        StoreResponse.StoreCreateResultDto response = storeCommandService.saveStore(regionId, request);
        return ApiResponse.onSuccess(response);
    }
}
