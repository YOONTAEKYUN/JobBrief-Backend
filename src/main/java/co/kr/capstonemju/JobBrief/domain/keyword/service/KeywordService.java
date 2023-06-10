package co.kr.capstonemju.JobBrief.domain.keyword.service;

import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsDto;
import co.kr.capstonemju.JobBrief.domain.news.controller.dto.NewsListDto;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
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
    private static final int PAGE_SIZE = 10;

    public NewsListDto searchNewsListByKeyword(String keyword, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        List<News> newsList = newsRepository.findByKeywordsKeywordNameContaining(keyword, pageRequest);
        List<NewsDto> newsDtoList = newsList.stream()
                .map(NewsDto::new)
                .collect(Collectors.toList());
        long totalItems = newsRepository.countByKeywordsKeywordNameContaining(keyword);
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);
        return new NewsListDto(newsDtoList, page, totalPages);
    }
}