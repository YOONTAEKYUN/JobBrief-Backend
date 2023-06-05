package co.kr.capstonemju.JobBrief.domain.member.controller.dto;

import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoDto {
    private long id;
    private String userId;
    private String name;
    private String password;
    private String phoneNumber;
    @Email
    private String email;

    public MemberInfoDto(Long id,String userId, String name, String password, String phoneNumber, String email){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;

    }
}
