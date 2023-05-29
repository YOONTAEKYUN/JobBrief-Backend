package co.kr.capstonemju.JobBrief.domain.auth.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequestDto {
    @NotEmpty(message = "userId는 필수 입력값입니다.")
    private String userId;
    @NotEmpty(message = "password 필수 입력값입니다.")
    private String password;

}
