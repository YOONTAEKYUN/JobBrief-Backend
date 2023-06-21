package co.kr.capstonemju.JobBrief.domain.bookmark.repository;

import co.kr.capstonemju.JobBrief.domain.bookmark.model.Bookmark;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Page<Bookmark> findByMember(Member member, Pageable pageable);

    Bookmark findByNewsAndMember(News news, Member member);

    long countByMember(Member member);
}
