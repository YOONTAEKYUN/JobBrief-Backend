package co.kr.capstonemju.JobBrief.domain.bookmark.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookmarkDto {
    private Long newsId;

    public BookmarkDto(Long newsId){
        this.newsId = newsId;
    }
}
