package co.kr.capstonemju.JobBrief.domain.keyword.controlloer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KeywordDto {
    private String keywordName;

    public KeywordDto(String keywordName) {
        this.keywordName = keywordName;
    }
}
