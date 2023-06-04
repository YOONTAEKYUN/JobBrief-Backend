package co.kr.capstonemju.JobBrief.domain.recentNews.repository;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.domain.recentNews.model.RecentNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecentNewsRepository extends JpaRepository<RecentNews, Long> {
    @Query("SELECT rn FROM RecentNews rn WHERE rn.member = :member ORDER BY rn.viewedAt DESC LIMIT 10")
    List<RecentNews> findTop10ByMemberOrderByViewedAtDesc(Member member);

    boolean existsByMemberAndNews(Member member, News news);
}
