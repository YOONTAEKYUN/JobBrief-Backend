package co.kr.capstonemju.JobBrief.domain.news.controller.dto;

import co.kr.capstonemju.JobBrief.domain.news.model.News;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsDto {
    @NotNull
    private Long id;
    @NotBlank
    private String title;

    private String reporter;
    private String press;
    @NotNull
    private LocalDateTime pub_date;
    @NotBlank
    private String summary;
    @NotBlank
    private String job;

    public NewsDto(News news){
        this.id = news.getId();
        this.title = news.getTitle();
        this.reporter = news.getReporter();
        this.press = news.getPress();
        this.pub_date = news.getPub_date();
        this.summary = news.getSummary();
        this.job = news.getJob();
    }
}
