package co.kr.capstonemju.JobBrief.domain.news.service;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.model.Role;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDetailDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.model.Job;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import co.kr.capstonemju.JobBrief.domain.scrap.model.Scrap;
import co.kr.capstonemju.JobBrief.domain.scrap.repository.ScrapRepository;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;

    private final ScrapRepository scrapRepository;

    public NewsListDto getNewsList(String job) {
        List<News> newsList = new ArrayList<>();
        switch (job) {
            case "all"-> newsList = newsRepository.findAllByOrderByPub_dateDesc();
            case "production-quality" -> newsList = newsRepository.findByJob(Job.PRODUCTION_QUALITY);
            case "it-developer" -> newsList = newsRepository.findByJob(Job.IT_DEVELOPMENT);
            case "human-affairs" -> newsList = newsRepository.findByJob(Job.HUMAN_AFFAIRS);
            case "finance-accounting" -> newsList = newsRepository.findByJob(Job.FINANCE_ACCOUNTING);
            case "strategy-planning" -> newsList = newsRepository.findByJob(Job.STRATEGY_PLANNING);
            case "sales-management" -> newsList = newsRepository.findByJob(Job.SALES_MANAGEMENT);
            case "marketing-merchandiser" -> newsList = newsRepository.findByJob(Job.MARKETING_MERCHANDISER);
            default -> System.out.println("올바르지 않은 값이 들어왔습니다");
        }
        List<NewsDto> newsDtoList = newsList.stream().map(NewsDto::new).toList();
        return new NewsListDto(newsDtoList);
    }

    public NewsListDto searchNewsList(String type, String keyword) {
        List<News> newsList = new ArrayList<>();
        switch (type) {
            case "title"-> newsList = newsRepository.findByTitleContaining(keyword);
            case "reporter" -> newsList = newsRepository.findByReporterContaining(keyword);
            case "content" -> newsList = newsRepository.findByContentContaining(keyword);
            case "press" -> newsList = newsRepository.findByPressContaining(keyword);
            default -> System.out.println("type에 올바르지 않은 값이 들어왔습니다.");
        }
        List<NewsDto> newsDtoList = newsList.stream().map(NewsDto::new).toList();
        return new NewsListDto(newsDtoList);
    }

    public NewsDetailDto getNewsDetail(Long id, @CurrentUser Member member) {
        boolean isScraped = false;
        NewsDetailDto newsDetailDTO = new NewsDetailDto();
        News news =newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("News Not Found"));

        if(member != null){
            boolean isMember = member.getRole().equals(Role.MEMBER);
            if (isMember){
                Scrap scrap = scrapRepository.findByNewsAndMember(news, member);
                if (scrap != null) {
                    isScraped = true;
                }//회원인데 해당 기사에 대해 스크랩이 없는 경우, ""
                String scrap_opinion = isScraped ? scrap.getOpinion() : "";
                newsDetailDTO = new NewsDetailDto(news, scrap_opinion);
            }else {//비회원인 경우, 스크랩 내용은 null 로 보냄
                newsDetailDTO = new NewsDetailDto(news, null);
            }
        }
        return newsDetailDTO;
    }

}
