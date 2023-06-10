package co.kr.capstonemju.JobBrief.domain.keyword.service;

import co.kr.capstonemju.JobBrief.domain.bookmark.model.Bookmark;
import co.kr.capstonemju.JobBrief.domain.bookmark.repository.BookmarkRepository;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsForMemberDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListForMemberDto;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {
    private final NewsRepository newsRepository;
    private final BookmarkRepository bookmarkRepository;
    private static final int PAGE_SIZE = 10;

    public NewsListForMemberDto searchNewsListByKeyword(Member member, String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);

        if (member != null) {
            // 회원인 경우
            List<News> newsList = newsRepository.findByKeywordsKeywordNameContaining(keyword, pageRequest);


            List<NewsForMemberDto> newsForMemberDtoList = newsList.stream()
                    .map(news -> {
                        // 각 뉴스마다 북마크 상태를 확인하여 설정합니다.
                        boolean isBookmarked = bookmarkRepository.findByNewsAndMember(news, member) != null;
                        return new NewsForMemberDto(news, isBookmarked);
                    })
                    .collect(Collectors.toList());

            long totalItems = newsRepository.countByKeywordsKeywordNameContaining(keyword);
            int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
            return new NewsListForMemberDto(newsForMemberDtoList, page, totalPages);
        }
        else {
            // 비회원인 경우
            List<News> newsList = newsRepository.findByKeywordsKeywordNameContaining(keyword, pageRequest);
            List<NewsForMemberDto> newsForMemberDtoList = newsList.stream()
                    .map(news -> new NewsForMemberDto(news, false))
                    .collect(Collectors.toList());

            long totalItems = newsRepository.countByKeywordsKeywordNameContaining(keyword);
            int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
            return new NewsListForMemberDto(newsForMemberDtoList, page, totalPages);
        }


    }
}