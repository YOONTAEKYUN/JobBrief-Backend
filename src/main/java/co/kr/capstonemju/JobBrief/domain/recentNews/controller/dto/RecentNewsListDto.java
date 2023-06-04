package co.kr.capstonemju.JobBrief.domain.recentNews.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecentNewsListDto {
    List<RecentNewsDto> recentnewsList;
}
