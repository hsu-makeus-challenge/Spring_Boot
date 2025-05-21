package umc.spring.domain.temp.service;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import umc.spring.domain.member.data.mapping.MemberMission;
import umc.spring.domain.member.repository.memberMission.MemberMissionRepository;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            log.info("");
            throw new ErrorHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }

    // tmp
    @Override
    public Slice<MemberMissionDto> findAllSlice() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<MemberMission> memberMissionList = memberMissionRepository.findAll(pageRequest);
        List<MemberMissionDto> dtoList = memberMissionList.stream()
                .map(memberMission -> MemberMissionDto.builder()
                        .id(memberMission.getId())
                        .build()
                ).toList();
        return new PageImpl<>(dtoList);
    }

    @Override
    public Page<MemberMissionDto> findAllPage() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<MemberMission> memberMissionList = memberMissionRepository.findAll();
        List<MemberMissionDto> dtoList = memberMissionList.stream()
                .map(memberMission -> MemberMissionDto.builder()
                        .id(memberMission.getId())
                        .build()
                ).toList();
        return new PageImpl<>(dtoList);
    }

    @Getter
    @Builder
    public static class MemberMissionDto{
        private Long id;
    }

}
