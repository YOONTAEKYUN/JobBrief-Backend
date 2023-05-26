package co.kr.capstonemju.JobBrief.domain.scrap.model;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import co.kr.capstonemju.JobBrief.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "scrap")
public class Scrap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id", unique = true)
    private News news;

    @ManyToOne
    @JoinColumn(name = "member_id", unique = true)
    private Member member;

    @Column(name = "opinion")
    private String opinion;
}
