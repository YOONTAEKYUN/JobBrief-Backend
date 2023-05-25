package co.kr.capstonemju.JobBrief.global.config;

import co.kr.capstonemju.JobBrief.domain.auth.jwt.JwtProvider;
import co.kr.capstonemju.JobBrief.domain.auth.exception.JwtAccessDeniedHandler;
import co.kr.capstonemju.JobBrief.domain.auth.exception.JwtAuthenticationEntryPoint;
import co.kr.capstonemju.JobBrief.domain.auth.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
            .httpBasic().disable()
            .csrf().disable()
            .cors()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(jwtAccessDeniedHandler)
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/member/join","/api/auth/login","/api/news/**").permitAll()
            .requestMatchers(HttpMethod.GET,"/api/collection/**").hasRole("MEMBER")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(new JwtFilter(jwtProvider),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

