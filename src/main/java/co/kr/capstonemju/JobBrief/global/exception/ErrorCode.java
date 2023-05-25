package co.kr.capstonemju.JobBrief.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USERID_DUPLICATED(HttpStatus.CONFLICT,"사용자 아이디 DUPLICATE "),
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT,"사용자 이메일 DUPLICATE "),
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND,"사용자 아이디 NOT FOUND "),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"사용자 비밀번호 INVALID");
    private HttpStatus httpStatus;
    private String message;
}
