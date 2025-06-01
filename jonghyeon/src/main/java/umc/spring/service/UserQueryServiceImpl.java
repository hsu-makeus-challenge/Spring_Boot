package umc.spring.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.exception.ErrorStatus;
import umc.spring.apiPayload.exception.handler.UserHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.converter.UserConverter;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserResponseDTO;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return UserConverter.toUserInfoDTO(user);
    }
}
