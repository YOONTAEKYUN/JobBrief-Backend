package co.kr.capstonemju.JobBrief.domain.member.service;

import co.kr.capstonemju.JobBrief.domain.member.controller.dto.MemberInfoDto;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.repository.MemberRepository;
import co.kr.capstonemju.JobBrief.global.exception.AppException;
import co.kr.capstonemju.JobBrief.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void join(Member member){
        memberRepository.findByUserId(member.getUserId())
                        .ifPresent(member1 -> {
                            throw new AppException(ErrorCode.USERID_DUPLICATED,member.getUserId() + "는 이미 있습니다.");
                        });
        memberRepository.save(member);
    }

    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId)
                .orElseThrow(()-> new IllegalArgumentException(""));
    }

    public MemberInfoDto getInfo(Member member) {
        MemberInfoDto memberInfoDto = new MemberInfoDto(
                member.getId(),
                member.getUserId(),
                member.getName(),
                member.getPassword(),
                member.getPhoneNumber(),
                member.getEmail()
        );
        return memberInfoDto;
    }

    public void updateInfo(Member member, MemberInfoDto memberInfoDto) {

    }
}
