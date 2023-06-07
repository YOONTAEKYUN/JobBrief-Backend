package co.kr.capstonemju.JobBrief.domain.member.service;

import co.kr.capstonemju.JobBrief.domain.member.controller.dto.IdCheckDto;
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

    public MemberInfoDto updateInfo(Member member, MemberInfoDto memberInfoDto) {
        Member updateMember = memberRepository.findByUserId(member.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(""));

        updateMember.updateMemberInfo(
                memberInfoDto.getUserId(),
                memberInfoDto.getName(),
                memberInfoDto.getPassword(),
                memberInfoDto.getPhoneNumber(),
                memberInfoDto.getEmail()
        );

        memberRepository.save(updateMember);  // 수정된 회원 정보 저장

        return new MemberInfoDto(
                updateMember.getId(),
                updateMember.getUserId(),
                updateMember.getName(),
                updateMember.getPassword(),
                updateMember.getPhoneNumber(),
                updateMember.getEmail()
        );
    }

    public IdCheckDto idCheck(String userId) {
        boolean isDuplicated = memberRepository.findByUserId(userId).isEmpty();
        return new IdCheckDto(isDuplicated, userId);
    }
}
