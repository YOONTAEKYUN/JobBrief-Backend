package co.kr.capstonemju.JobBrief.domain.member.controller.dto;

import co.kr.capstonemju.JobBrief.domain.member.model.Member;
import co.kr.capstonemju.JobBrief.domain.member.model.Role;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinMemberRequest {
    private long id;
    private String userId;
    private String name;
    private String password;
    private String phoneNumber;
    @Email
    private String email;
    private String studentId;

    public Member newMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .userId(userId)
                .name(name)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .email(email)
                .studentId(studentId)
                .role(Role.MEMBER)
                .build();
    }
}
