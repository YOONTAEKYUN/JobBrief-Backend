package co.kr.capstonemju.JobBrief.domain.bookmark.model;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
