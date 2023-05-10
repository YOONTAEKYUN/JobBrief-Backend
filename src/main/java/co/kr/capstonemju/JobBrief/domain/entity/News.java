package co.kr.capstonemju.JobBrief.domain.entity;

//import co.kr.capstonemju.JobBrief.domain.Job;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.List;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsId")
    private Integer newsId;

    @Column(name = "newsTitle",nullable = false, length = 100)
    private String newsTitle;

    @Column(name = "reporter",nullable = false, length = 20)
    private String reporter;

    @Column(name = "press",nullable = false, length = 20)
    private String press;

    @Column(name = "pub_date",nullable = false)
    private LocalDateTime pub_date;

    @Column(name = "newsContent",nullable = false)
    private String newsContent;

    @Column(name = "summary",nullable = false)
    private String summary;

    @OneToOne
    @JoinColumn(name = "jobId")
    private Job jobId;
}
