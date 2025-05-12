package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.StoreMissionService.StoreMissionCommandService;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.storeMission.StoreMissionResponse;

@Tag(name = "가게 미션 페이지", description = "가게 미션에 관한 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/missions/{missionId}/stores/{storeId}")
public class StoreMissionController {

    private final StoreMissionCommandService storeMissionCommandService;

    @Operation(
            summary = "가게에 미션 추가",
            description = "가게에 missionId와 일치하는 미션을 할당하는 API 입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION4001", description = "아이디와 일치하는 미션이 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "아이디와 일치하는 가게가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "STORE4001", description = "아이디와 일치하는 가게가 없습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @PostMapping
    public ApiResponse<StoreMissionResponse.StoreMissionCreateResultDto> postStoreMissionByMission(
            @Parameter(description = "미션 아이디", example = "1", required = true)
            @PathVariable @ExistMission Long missionId,

            @Parameter(description = "가게 아이디", example = "1", required = true)
            @PathVariable @ExistStore Long storeId
    ) {
        StoreMissionResponse.StoreMissionCreateResultDto response = storeMissionCommandService.saveStoreMission(storeId, missionId);
        return ApiResponse.onSuccess(response);
    }

}
