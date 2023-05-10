package co.kr.capstonemju.JobBrief.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "hashtag")
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtagId")
    private Integer hashtagId;

    @Column(name = "hashtagName")
    private String hashtagName;

}
