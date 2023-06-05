package co.kr.capstonemju.JobBrief.domain.member.controller;

import co.kr.capstonemju.JobBrief.domain.member.controller.dto.MemberInfoDto;
import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.service.MemberService;
import co.kr.capstonemju.JobBrief.global.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import co.kr.capstonemju.JobBrief.domain.member.controller.dto.JoinMemberRequest;

@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder encoder;

    @PostMapping("/join")
    private void join(@RequestBody @Valid JoinMemberRequest requestDTO) {
        memberService.join(requestDTO.newMember(encoder));
    }

    @GetMapping("/info")
    private MemberInfoDto getInfo(@CurrentUser Member member){
        return memberService.getInfo(member);
    }

    @PostMapping("/info")
    private void updateInfo(@CurrentUser Member member, @RequestBody MemberInfoDto memberInfoDto){
        memberService.updateInfo(member, memberInfoDto);
    }

}