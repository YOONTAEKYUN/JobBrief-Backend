package co.kr.capstonemju.JobBrief.domain.auth.service;

import co.kr.capstonemju.JobBrief.domain.auth.model.PrincipalDetails;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public PrincipalDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member findUser = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 id를 가진 회원을 찾을 수 없습니다 -> " + userId));

        if(findUser != null){
            PrincipalDetails principalDetails = new PrincipalDetails(findUser);
            return  principalDetails;
        }

        return null;
    }

}
