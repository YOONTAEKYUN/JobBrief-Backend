package co.kr.capstonemju.JobBrief.global.config;

import co.kr.capstonemju.JobBrief.domain.auth.jwt.JwtProvider;
import co.kr.capstonemju.JobBrief.domain.auth.exception.JwtAccessDeniedHandler;
import co.kr.capstonemju.JobBrief.domain.auth.exception.JwtAuthenticationEntryPoint;
import co.kr.capstonemju.JobBrief.domain.auth.jwt.JwtFilter;
import co.kr.capstonemju.JobBrief.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String API_PREFIX = "/api";

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtProvider jwtProvider;

    private final MemberService memberService;

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
            .requestMatchers(
                    API_PREFIX+"/member/join",
                    API_PREFIX+"/auth/login"
            ).permitAll()
            .requestMatchers(API_PREFIX+"/collection/**").hasRole("MEMBER")
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtFilter jwtAuthFilter() {
        return new JwtFilter(jwtProvider, memberService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



