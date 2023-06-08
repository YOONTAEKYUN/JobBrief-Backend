package co.kr.capstonemju.JobBrief.domain.news.repository;

import co.kr.capstonemju.JobBrief.domain.news.model.News;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTitleContaining(String keyword);

    List<News> findByContentContaining(String keyword);

    List<News> findByReporterContaining(String keyword);

    List<News> findByPressContaining(String keyword);

    @Query(value = "SELECT * FROM news n",
            countQuery = "SELECT count(*) FROM news n", nativeQuery = true)
    Page<News> findAllByOrderByPubDateDesc(Pageable pageable);

    @Query(value = "SELECT * FROM news n WHERE n.job = :job ORDER BY n.pub_date DESC",
            countQuery = "SELECT count(*) FROM news n WHERE n.job = :job",
            nativeQuery = true)
    Page<News> findByJobOrderByPubDateDesc(@Param("job") String job, Pageable pageable);

}
