package com.example.selfdev.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // REST API이므로 CSRF, 비활성화
            .csrf(AbstractHttpConfigurer::disable)
            // JWT를 사용하기 때문에 세션을 사용하지 않음
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // H2 Console을 위한 설정 (IFrame 허용)
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            // API 권한 설정
            .authorizeHttpRequests(auth -> auth
                // 로그인, 회원가입 API는 토큰 없이 접근 허용
                .requestMatchers("/api/users/login", "/api/users/signup").permitAll()
                // Swagger UI 및 API Docs 허용
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                // H2 Console 접속 허용
                .requestMatchers("/h2-console/**").permitAll()
                // 게시글 조회(GET)는 누구나 가능
                .requestMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
                // 그 외 모든 요청은 인증 필요 (토큰 검증)
                .anyRequest().authenticated()
            )
            // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 비밀번호 암호화를 위한 PasswordEncoder 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
