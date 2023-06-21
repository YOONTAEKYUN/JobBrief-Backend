package co.kr.capstonemju.JobBrief.domain.recentNews.service;

import co.kr.capstonemju.JobBrief.domain.bookmark.repository.BookmarkRepository;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.recentNews.controller.dto.RecentNewsDto;
import co.kr.capstonemju.JobBrief.domain.recentNews.controller.dto.RecentNewsListDto;
import co.kr.capstonemju.JobBrief.domain.recentNews.model.RecentNews;
import co.kr.capstonemju.JobBrief.domain.recentNews.repository.RecentNewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecentNewsService {
    private final RecentNewsRepository recentNewsRepository;
    private final BookmarkRepository bookmarkRepository;

    public RecentNewsListDto getRecentNewsList(Member member) {
        List<RecentNews> recentNewsList = recentNewsRepository.findTop10ByMemberOrderByViewedAtDesc(member);
        List<RecentNewsDto> recentNewsDtoList = recentNewsList.stream().map(recentNews -> {
            News news = recentNews.getNews();
            boolean isBookmarked = bookmarkRepository.findByNewsAndMember(news, member) != null;
            return new RecentNewsDto(recentNews, isBookmarked);
        }).toList();

        return new RecentNewsListDto(recentNewsDtoList);
    }

    public void saveRecentNews(RecentNews recentNews) {
        boolean exists = recentNewsRepository.existsByMemberAndNews(recentNews.getMember(), recentNews.getNews());
        if (!exists) {
            recentNewsRepository.save(recentNews);
        }
    }
}

