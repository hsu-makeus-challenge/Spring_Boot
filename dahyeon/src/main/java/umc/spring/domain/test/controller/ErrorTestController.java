package umc.spring.domain.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ErrorTestController {

  @GetMapping("/error")
  public String throwError() {
    // 일부러 예외 발생
    throw new RuntimeException("테스트용 RuntimeException 발생");
  }
}
