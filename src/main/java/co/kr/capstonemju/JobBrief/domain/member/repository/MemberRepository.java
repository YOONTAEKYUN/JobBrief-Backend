package co.kr.capstonemju.JobBrief.domain.member.repository;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member>findByUserId(String id);
}
