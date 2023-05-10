package co.kr.capstonemju.JobBrief.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "news_hashtag")
public class News_HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "newsId")
    private News newsId;

    @ManyToOne
    @JoinColumn(name = "hashtagId")
    private HashTag hashTagId;
}
