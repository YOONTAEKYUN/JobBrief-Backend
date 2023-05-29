package co.kr.capstonemju.JobBrief.domain.auth.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private String tokenType;
    private String accessToken;
    private String role;

    public LoginResponseDto(String tokenType, String accessToken, String role) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.role = role;
    }
}
