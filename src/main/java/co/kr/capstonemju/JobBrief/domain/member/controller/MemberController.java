package co.kr.capstonemju.JobBrief.domain.member.controller;

import co.kr.capstonemju.JobBrief.domain.member.service.MemberService;
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

}