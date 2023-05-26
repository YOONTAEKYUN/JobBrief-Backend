package co.kr.capstonemju.JobBrief.domain.news.model;

import co.kr.capstonemju.JobBrief.domain.keyword.model.Keyword;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title",nullable = false, length = 100)
    private String title;

    @Column(name = "reporter",nullable = false, length = 20)
    private String reporter;

    @Column(name = "press",nullable = false, length = 20)
    private String press;

    @Column(name = "pub_date",nullable = false)
    private LocalDateTime pub_date;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "summary",nullable = false)
    private String summary;

    @Column(name = "job",nullable = false)
    @Enumerated(EnumType.STRING)
    private Job job;

    @ManyToMany
    @JoinTable(name = "news_keywords",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private List<Keyword> keywords = new ArrayList<>();

    @Builder
    private News(String title,String reporter, String press, LocalDateTime pub_date, String content, String summary,List<Keyword> keywords ,Job job){
        this.title = title;
        this.reporter = reporter;
        this.press = press;
        this.pub_date = pub_date;
        this.content = content;
        this.summary = summary;
        this.keywords = keywords;
        this.job = job;
    }

}