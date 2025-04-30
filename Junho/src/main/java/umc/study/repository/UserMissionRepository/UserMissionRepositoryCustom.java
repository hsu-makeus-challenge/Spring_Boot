package umc.study.repository.UserMissionRepository;

import umc.study.web.dto.UserMissionDto;

import java.util.*;

public interface UserMissionRepositoryCustom {
    List<UserMissionDto> findClearedMissionsWithStore();
    List<UserMissionDto> findNotClearedMissionsWithStore();
}