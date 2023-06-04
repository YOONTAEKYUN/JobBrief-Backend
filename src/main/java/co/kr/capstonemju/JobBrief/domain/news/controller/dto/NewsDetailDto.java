package co.kr.capstonemju.JobBrief.domain.news.controller.dto;

import co.kr.capstonemju.JobBrief.domain.keyword.controlloer.dto.KeywordDto;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class NewsDetailDto {
    private Long id;
    private String title;
    private String reporter;
    private String press;
    private LocalDateTime pub_date;
    private String content;
    private String summary;
    private String scrap_opinion;
    private boolean isMember;
    private List<KeywordDto> keywords;

    public NewsDetailDto(News news, String scrap_opinion, boolean isMember) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.press = news.getPress();
        this.reporter = news.getReporter();
        this.pub_date = news.getPub_date();
        this.content = news.getContent();
        this.summary = news.getSummary();
        this.scrap_opinion = scrap_opinion;
        this.isMember = isMember;
        this.keywords = news.getKeywords().stream()
                .map(keyword -> new KeywordDto(keyword.getKeywordName()))
                .collect(Collectors.toList());
    }
}