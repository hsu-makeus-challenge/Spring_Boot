package umc.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc.spring.validation.PageArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final PageArgumentResolver existPageArgumentResolver;

	public WebConfig(PageArgumentResolver resolver) {
		this.existPageArgumentResolver = resolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(existPageArgumentResolver);
	}
}

