package umc.spring.global.common.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.spring.global.common.apiPayload.ApiResponse;
import umc.spring.global.common.apiPayload.code.status.ErrorStatus;
import umc.spring.global.common.config.security.jwt.JwtAuthenticationFilter;
import umc.spring.global.common.config.security.jwt.JwtTokenProvider;
import umc.spring.global.common.config.security.social.CustomOAuth2UserService;
import umc.spring.global.common.config.security.social.OAuth2SuccessHandler;

import java.util.List;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper, CustomOAuth2UserService customOAuth2UserService, OAuth2SuccessHandler oAuth2SuccessHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors( cors ->
                    cors.configurationSource(request -> {
                        var config = new org.springframework.web.cors.CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:8080")); // Swagger UI origin
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowCredentials(true);
                        return config;
                    })
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home", "/signup", "/css/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                        .requestMatchers("/api/token/**").permitAll()
                        .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, objectMapper), UsernamePasswordAuthenticationFilter.class)
                // 인증 실패
                .exceptionHandling((exception) -> {
                    exception.authenticationEntryPoint((request, response, authException) -> {
                        log.info("request = {}", request);
                        log.info("exception = {}", authException.getMessage());
                        log.info("1");
                        Object ex = request.getAttribute("exception");
                        log.info("request.getAttribute = {}", ex);

                        if (ex == ErrorStatus.TOKEN_EXPIRED) {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            objectMapper.writeValue(
                                    response.getOutputStream(),
                                    ApiResponse.onFailure(ErrorStatus.TOKEN_EXPIRED.getCode(), ErrorStatus.TOKEN_EXPIRED.getMessage(), null)
                            );
                            return;
                        }

                        log.info("2");
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        objectMapper.writeValue(
                                response.getOutputStream(),
                                ApiResponse.onFailure(ErrorStatus.FAIL_AUTHENTICATION.getCode(), ErrorStatus.FAIL_AUTHENTICATION.getMessage(), null)
                        );
                    });
                })
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // ★ 이거 꼭 필요
                        )
                        .successHandler(oAuth2SuccessHandler) // 별도 핸들러 등록
                )

//                .oauth2Login(oauth2 -> oauth2
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(customOAuth2UserService)
//                        )
//                        .successHandler((request, response, authentication) -> {
//
//
//                            // OAuth2User에서 필요한 사용자 정보 추출
//                            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//
//                            String name = oAuth2User.getAttribute("name");
//                            String email = oAuth2User.getAttribute("email");
//
//                            // JWT 토큰 생성
//                            String jwt = jwtTokenProvider.generateToken(name, email, "USER");
//
//                            // JWT 토큰을 헤더에 추가
////                            response.setHeader("Authorization", "Bearer " + jwt);
//
//                            // 리디렉션
//                            response.sendRedirect("/home?token=" + jwt);
//                        })
//                )
        ;

            return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
