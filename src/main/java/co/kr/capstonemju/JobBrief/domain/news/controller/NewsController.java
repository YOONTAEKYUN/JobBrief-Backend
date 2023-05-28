package co.kr.capstonemju.JobBrief.domain.news.controller;

import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.service.NewsService;
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
}
