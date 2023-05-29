package co.kr.capstonemju.JobBrief.domain.news.controller;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDetailDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.service.NewsService;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public NewsListDto getNewsList(@RequestParam String job){
        return newsService.getNewsList(job);
    }

    @GetMapping("/search")
    public NewsListDto searchNewsList(@RequestParam String type, String keyword){
        return newsService.searchNewsList(type, keyword);
    }

    @GetMapping("/{id}")
    public NewsDetailDto getNewsDetail(@PathVariable Long id, @CurrentUser Member member){
        return newsService.getNewsDetail(id, member);
    }
}
