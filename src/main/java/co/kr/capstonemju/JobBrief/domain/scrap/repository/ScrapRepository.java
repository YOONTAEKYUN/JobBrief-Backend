package co.kr.capstonemju.JobBrief.domain.scrap.repository;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.scrap.model.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Scrap findByNewsAndMember(News news, Member member);
    List<Scrap> findByMember(Member member);
}
