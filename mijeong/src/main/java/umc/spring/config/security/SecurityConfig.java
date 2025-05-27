package umc.spring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정 활성화
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 접근 제어 설정
                .authorizeHttpRequests((requests) -> requests
                        // .requestMatchers(): 특정 URL 패턴에 대한 접근 권한 설정
                        .requestMatchers("/", "/home", "/signup", "/users/signup", "/css/**").permitAll()  // permitAll(): 인증 없이 접근 가능한 경로 지정
                        .requestMatchers("/admin/**").hasRole("ADMIN") // hasRole(): 역할을 가진 사용자만 접근 가능하도록 제한
                        .anyRequest().authenticated() // 그 외 모든 요청에 대한 인증 요구
                )
                // 폼 기반 로그인 설정
                .formLogin((form) -> form
                        .loginPage("/login") // 커스텀 로그인 페이지
                        .defaultSuccessUrl("/home", true) // 로그인 성공 시 리다이렉트
                        .permitAll() // 모든 사용자가 접근 가능하도록 설정
                )
                // 로그아웃 처리에 대한 설정
                .logout((logout) -> logout
                        .logoutUrl("/logout") // 로그아웃 경로 처리
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 시 리다이렉트
                        .permitAll()
                );

        return http.build();
    }

    // 비밀번호 암호황
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}