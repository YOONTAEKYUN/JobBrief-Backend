package co.kr.capstonemju.JobBrief.domain.news.repository;

import co.kr.capstonemju.JobBrief.domain.news.model.Job;
import co.kr.capstonemju.JobBrief.domain.news.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(value = "SELECT * FROM news n ORDER BY n.pub_date DESC", nativeQuery = true)
    List<News> findAllByOrderByPub_dateDesc();

    List<News> findByJob(Job job);

    @Query(value = "SELECT * FROM news n",
            countQuery = "SELECT count(*) FROM news n", nativeQuery = true)
    Page<News> findAllByOrderByPub_dateDesc(Pageable pageable);

}
