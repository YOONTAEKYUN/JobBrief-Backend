package co.kr.capstonemju.JobBrief.domain.recentNews.controller;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.recentNews.controller.dto.RecentNewsListDto;
import co.kr.capstonemju.JobBrief.domain.recentNews.service.RecentNewsService;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recent-news")
@RequiredArgsConstructor
public class RecentNewsController {

    private final RecentNewsService recentNewsService;

    @GetMapping
    @PreAuthorize("hasRole('MEMBER')")
    public RecentNewsListDto getRecentNewsList(@CurrentUser Member member){
        return recentNewsService.getRecentNewsList(member);
    }
}
