package co.kr.capstonemju.JobBrief.domain.keyword.model;

import co.kr.capstonemju.JobBrief.domain.news.model.News;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "keyword")
    private String keyword;

    @ManyToMany(mappedBy = "keywords")
    private List<News> newsList = new ArrayList<>();

}
