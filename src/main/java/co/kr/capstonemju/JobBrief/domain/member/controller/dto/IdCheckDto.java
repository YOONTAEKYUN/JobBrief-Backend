package co.kr.capstonemju.JobBrief.domain.member.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdCheckDto {
    private boolean isDuplicated;
    private String userId;

    public IdCheckDto(boolean isDuplicated, String userId){
        this.isDuplicated = isDuplicated;
        this.userId =userId;
    }
}
