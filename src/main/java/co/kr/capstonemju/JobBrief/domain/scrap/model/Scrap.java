package co.kr.capstonemju.JobBrief.domain.scrap.model;

import co.kr.capstonemju.JobBrief.domain.news.model.News;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "news_id", unique = true)
    private News news;

    @Column(name = "opinion")
    private String opinion;
}
