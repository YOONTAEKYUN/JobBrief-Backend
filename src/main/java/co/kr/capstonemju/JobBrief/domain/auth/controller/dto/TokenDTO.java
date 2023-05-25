package co.kr.capstonemju.JobBrief.domain.auth.controller.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDTO {

    private String accessToken;
    private String refreshToken;

    public TokenDTO(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
