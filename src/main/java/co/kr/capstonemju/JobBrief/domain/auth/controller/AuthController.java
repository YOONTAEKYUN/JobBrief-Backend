package co.kr.capstonemju.JobBrief.domain.auth.controller;

import co.kr.capstonemju.JobBrief.domain.auth.controller.dto.LoginRequestDto;
import co.kr.capstonemju.JobBrief.domain.auth.controller.dto.LoginResponseDto;
import co.kr.capstonemju.JobBrief.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto loginRequest) {
        return authService.login(loginRequest);
    }


}