package umc.spring.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import umc.spring.web.dto.UserResponseDTO;

@Service
public interface UserQueryService {

    public UserResponseDTO.UserInfoDTO getUserInfo(HttpServletRequest request);
}
