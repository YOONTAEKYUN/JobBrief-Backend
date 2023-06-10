package co.kr.capstonemju.JobBrief.domain.news.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NewsListForMemberDto {
    private List<NewsForMemberDto> newsList;
    private int currentPage;
    private int totalPages;

    public NewsListForMemberDto(List<NewsForMemberDto> newsList, int currentPage, int totalPages){
        this.newsList = newsList;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
