package co.kr.capstonemju.JobBrief.domain.auth.service;

import co.kr.capstonemju.JobBrief.domain.auth.controller.dto.LoginResponseDto;
import co.kr.capstonemju.JobBrief.domain.auth.controller.dto.LoginRequestDto;
import co.kr.capstonemju.JobBrief.domain.auth.jwt.JwtProvider;
import co.kr.capstonemju.JobBrief.domain.auth.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthService {
    private static final String TOKEN_TYPE = "Bearer";
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword(), null);
        Authentication authentication = authenticationManager.authenticate(authToken);
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String token = jwtProvider.createToken(principal);
        String role = principal.getAuthorities().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("")).getAuthority();
        return new LoginResponseDto(TOKEN_TYPE, token, role);
    }

}
