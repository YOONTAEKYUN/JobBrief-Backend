package co.kr.capstonemju.JobBrief.domain.scrap.service;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.news.repository.NewsRepository;
import co.kr.capstonemju.JobBrief.domain.scrap.controller.dto.ScrapCreateRequest;
import co.kr.capstonemju.JobBrief.domain.scrap.model.Scrap;
import co.kr.capstonemju.JobBrief.domain.scrap.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepository scrapRepository;
    private final NewsRepository newsRepository;

    public void saveScrap(Member member, Long id, ScrapCreateRequest scrapCreateRequest ) {
        News news =newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("News Not Found"));
        Scrap scrapCheck = scrapRepository.findByNewsAndMember(news,member);
        if (scrapCheck == null){
            //create scrap
            Scrap newScrap = Scrap.builder().news(news).member(member).opinion(scrapCreateRequest.getOpinion()).build();
            scrapRepository.save(newScrap);
        }else {
            //update scrap
            scrapCheck.updateScrap(scrapCreateRequest.getOpinion());
            scrapRepository.save(scrapCheck);
        }
    }

}
