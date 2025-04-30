package umc.spring.service.UserService;

public interface UserCommandService {
    void deleteUserWithRelatedEntities(Long userId);

}
