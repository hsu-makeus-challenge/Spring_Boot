package umc.spring.domain.temp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.apiPayload.exception.handler.ErrorHandler;

@Service
@Slf4j
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {

    @Override
    public void checkFlag(Integer flag) {
        if (flag == 1) {
            log.info("");
            throw new ErrorHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
