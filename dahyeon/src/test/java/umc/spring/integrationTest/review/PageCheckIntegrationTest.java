package umc.spring.integrationTest.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import umc.spring.domain.store.controller.StoreController;
import umc.spring.domain.store.repository.StoreRepository;
import umc.spring.global.validation.validator.PageArgumentResolver;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PageCheck 전역 예외 처리 통합 테스트")
class PageCheckIntegrationTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private StoreRepository storeRepository;

  @BeforeEach
  void setup() {
    given(storeRepository.existsById(1L)).willReturn(true);
  }

  @Test
  @DisplayName("page가 숫자가 아닌 경우 전역 예외 처리기에서 잡힘")
  void pageTypeMismatchExceptionHandled() throws Exception {
    mockMvc
        .perform(get("/stores/1/reviews").param("page", "abc").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.code").value("PAGE_002"))
        .andExpect(jsonPath("$.result").exists())
        .andExpect(jsonPath("$.messge").exists());
  }

  @Test
  @DisplayName("page 파라미터가 없는 경우 MissingServletRequestParameterException이 전역 예외 처리기에서 잡힘")
  void pageMissingParameterHandled() throws Exception {
    mockMvc
        .perform(get("/stores/1/reviews").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.code").value("COMMON400"))
        .andExpect(jsonPath("$.result").value("필수 파라미터가 누락되었습니다. 'page' 파라미터는 필수입니다."))
        .andExpect(jsonPath("$.messge").value("잘못된 요청입니다."));
  }

  @Test
  @DisplayName("RequestBody 검증 실패 시 예외 처리")
  void requestBodyValidationFailed() throws Exception {
    mockMvc
        .perform(
            post("/stores/1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\": \"리뷰입니다\", \"rating\": 5}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.isSuccess").value(false))
        .andExpect(jsonPath("$.code").value("COMMON400"))
        .andExpect(jsonPath("$.result").exists())
        .andExpect(jsonPath("$.messge").exists());
  }

  @Test
  @DisplayName("커스텀 리졸버가 동작하며 page-1로 반환되는지 검증")
  void pageArgumentResolver_verify() throws Exception {
    Method method = StoreController.class.getMethod("getReviewList", Long.class, Integer.class);
    MethodParameter parameter = new MethodParameter(method, 1);
    parameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());

    MockHttpServletRequest request = new MockHttpServletRequest("GET", "/stores/1/reviews");
    request.setParameter("page", "3");

    NativeWebRequest webRequest = new ServletWebRequest(request);

    PageArgumentResolver resolver = new PageArgumentResolver();
    Object result = resolver.resolveArgument(parameter, null, webRequest, null);

    assertEquals(2, result);
  }
}
