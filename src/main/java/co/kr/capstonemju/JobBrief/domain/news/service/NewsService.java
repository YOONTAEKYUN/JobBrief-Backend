package co.kr.capstonemju.JobBrief.domain.news.service;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.model.Role;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDetailDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.model.Job;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.recentNews.model.RecentNews;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import co.kr.capstonemju.JobBrief.domain.recentNews.service.RecentNewsService;
import co.kr.capstonemju.JobBrief.domain.scrap.model.Scrap;
import co.kr.capstonemju.JobBrief.domain.scrap.repository.ScrapRepository;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NewsService {
    private final NewsRepository newsRepository;
    private final RecentNewsService recentNewsService;
    private final ScrapRepository scrapRepository;
    private static final int PAGE_SIZE = 10;

    public NewsListDto getNewsList(String job, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("pub_date").descending());
        Page<News> newsPage = null;
        switch (job) {
            case "all"-> newsPage = newsRepository.findAllByOrderByPub_dateDesc(pageRequest);
            case "production-quality" -> newsPage = newsRepository.findByJob(Job.PRODUCTION_QUALITY, pageRequest);
            case "it-developer" -> newsPage = newsRepository.findByJob(Job.IT_DEVELOPMENT, pageRequest);
            case "human-affairs" -> newsPage = newsRepository.findByJob(Job.HUMAN_AFFAIRS, pageRequest);
            case "finance-accounting" -> newsPage = newsRepository.findByJob(Job.FINANCE_ACCOUNTING, pageRequest);
            case "strategy-planning" -> newsPage = newsRepository.findByJob(Job.STRATEGY_PLANNING, pageRequest);
            case "sales-management" -> newsPage = newsRepository.findByJob(Job.SALES_MANAGEMENT, pageRequest);
            case "marketing-merchandiser" -> newsPage = newsRepository.findByJob(Job.MARKETING_MERCHANDISER, pageRequest);
            default -> System.out.println("올바르지 않은 값이 들어왔습니다");
        }

        List<NewsDto> newsDtoList = newsPage.getContent().stream().map(NewsDto::new).toList();
        int totalPages = newsPage.getTotalPages();
        return new NewsListDto(newsDtoList, page, totalPages);
    }

    public NewsListDto searchNewsList(String type, String keyword, int page) {
        List<News> newsList = new ArrayList<>();
        switch (type) {
            case "title"-> newsList = newsRepository.findByTitleContaining(keyword);
            case "reporter" -> newsList = newsRepository.findByReporterContaining(keyword);
            case "content" -> newsList = newsRepository.findByContentContaining(keyword);
            case "press" -> newsList = newsRepository.findByPressContaining(keyword);
            default -> System.out.println("type에 올바르지 않은 값이 들어왔습니다.");
        }
        int totalNewsCount = newsList.size();
        int totalPages = (int) Math.ceil((double) totalNewsCount / PAGE_SIZE);

        // 현재 페이지에 해당하는 뉴스 목록 추출
        int startIndex = (page - 1) * PAGE_SIZE;
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalNewsCount);
        List<News> pagedNewsList = newsList.subList(startIndex, endIndex);

        List<NewsDto> newsDtoList = pagedNewsList.stream().map(NewsDto::new).toList();
        return new NewsListDto(newsDtoList, page, totalPages);
    }
    public NewsDetailDto getNewsDetail(Long newsId){
        News news =newsRepository.findById(newsId)
                .orElseThrow(() -> new NotFoundException("News Not Found"));
        return new NewsDetailDto(news, null, false);
    }
    public NewsDetailDto getNewsDetailForMember(Long newsId, @CurrentUser Member member) {
        boolean isScraped = false;
        News news =newsRepository.findById(newsId)
                .orElseThrow(() -> new NotFoundException("News Not Found"));

        RecentNews recentNews = new RecentNews();
        recentNews.setMember(member);
        recentNews.setNews(news);
        recentNews.setViewedAt(LocalDateTime.now());
        recentNewsService.saveRecentNews(recentNews);

        NewsDetailDto newsDetailDTO = new NewsDetailDto();
        if(member != null){
            boolean isMember = member.getRole().equals(Role.MEMBER);
            if (isMember){
                Scrap scrap = scrapRepository.findByNewsAndMember(news, member);
                if (scrap != null) {
                    isScraped = true;
                }//회원인데 해당 기사에 대해 스크랩이 없는 경우, ""
                String scrap_opinion = isScraped ? scrap.getOpinion() : "";
                newsDetailDTO = new NewsDetailDto(news, scrap_opinion, true);
            }
        }else {
            System.out.println("wrong access!");
        }
        return newsDetailDTO;
    }
}
