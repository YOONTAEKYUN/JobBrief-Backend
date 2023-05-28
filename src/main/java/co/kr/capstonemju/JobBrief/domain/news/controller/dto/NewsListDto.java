package co.kr.capstonemju.JobBrief.domain.news.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsListDto {
    List<NewsDto> newsList;
}
