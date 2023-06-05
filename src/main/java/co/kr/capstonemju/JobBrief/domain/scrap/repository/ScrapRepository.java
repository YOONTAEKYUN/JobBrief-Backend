package co.kr.capstonemju.JobBrief.domain.scrap.repository;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.scrap.model.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    Scrap findByNewsAndMember(News news, Member member);
    Page<Scrap> findByMember(Member member, Pageable pageable);
}
