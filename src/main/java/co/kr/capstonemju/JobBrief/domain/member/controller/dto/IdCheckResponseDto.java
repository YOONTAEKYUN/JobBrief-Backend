package co.kr.capstonemju.JobBrief.domain.member.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IdCheckResponseDto {
    private boolean isDuplicated;

    public IdCheckResponseDto(boolean isDuplicated){
        this.isDuplicated = isDuplicated;
    }
}
