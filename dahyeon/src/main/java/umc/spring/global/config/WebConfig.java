package umc.spring.global.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import umc.spring.global.validation.validator.PageArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final PageArgumentResolver pageArgumentResolver;

  public WebConfig(PageArgumentResolver pageArgumentResolver) {
    this.pageArgumentResolver = pageArgumentResolver;
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(pageArgumentResolver);
  }
}
