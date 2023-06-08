package co.kr.capstonemju.JobBrief.domain.recentNews.controller.dto;

import co.kr.capstonemju.JobBrief.domain.recentNews.model.RecentNews;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RecentNewsDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;

    private String reporter;
    private String press;
    @NotBlank
    private LocalDateTime pub_date;
    @NotBlank
    private String summary;
    @NotBlank
    private String job;

    public RecentNewsDto(RecentNews recentNews){
        this.id = recentNews.getId();
        this.title = recentNews.getNews().getTitle();
        this.reporter = recentNews.getNews().getReporter();
        this.press = recentNews.getNews().getPress();
        this.pub_date = recentNews.getNews().getPub_date();
        this.summary = recentNews.getNews().getSummary();
        this.job = recentNews.getNews().getJob();
    }
}
