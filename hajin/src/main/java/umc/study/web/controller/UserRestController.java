package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.UserConverter;
import umc.study.domain.Users;
import umc.study.service.UserService.UserCommandService;
import umc.study.web.dto.UserRequestDTO;
import umc.study.web.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;

    // Valid앞에 @RequestBody가 있었는데 없어도 되는거 아닌가?(의문)
    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDto request){
        Users user = userCommandService.joinMember(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }
}
