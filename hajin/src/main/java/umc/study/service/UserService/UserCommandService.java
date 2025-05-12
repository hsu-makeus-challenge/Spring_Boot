package umc.study.service.UserService;

import org.springframework.stereotype.Service;
import umc.study.domain.Users;
import umc.study.web.dto.UserRequestDTO;

@Service
public interface UserCommandService {
    public Users joinMember(UserRequestDTO.JoinDto request);
}
