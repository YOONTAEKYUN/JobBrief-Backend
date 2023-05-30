package co.kr.capstonemju.JobBrief.domain.bookmark.repository;

import co.kr.capstonemju.JobBrief.domain.bookmark.model.Bookmark;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByMember(Member member);


}
