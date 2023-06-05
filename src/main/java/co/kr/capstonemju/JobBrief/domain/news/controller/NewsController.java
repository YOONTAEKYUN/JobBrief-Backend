package co.kr.capstonemju.JobBrief.domain.news.controller;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDetailDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.service.NewsService;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public NewsListDto getNewsList(@RequestParam String job, @RequestParam(defaultValue = "1") int page){
        return newsService.getNewsList(job, page);
    }

    @GetMapping("/search")
    public NewsListDto searchNewsList(@RequestParam String type, String keyword, @RequestParam(defaultValue = "1") int page){
        return newsService.searchNewsList(type, keyword, page);
    }
    @GetMapping("/{newsId}")
    public NewsDetailDto getNewsDetail(@PathVariable Long newsId){
        return newsService.getNewsDetail(newsId);
    }
    @GetMapping("/member/{newsId}")
    @PreAuthorize("hasRole('MEMBER')")
    public NewsDetailDto getNewsDetailForMember(@PathVariable Long newsId, @CurrentUser Member member){
        return newsService.getNewsDetailForMember(newsId, member);
    }
}
