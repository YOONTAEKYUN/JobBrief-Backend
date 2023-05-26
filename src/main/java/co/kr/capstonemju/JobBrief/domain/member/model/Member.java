package co.kr.capstonemju.JobBrief.domain.member.model;

import co.kr.capstonemju.JobBrief.domain.member.controller.dto.JoinMemberRequest;
import co.kr.capstonemju.JobBrief.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "user_id",unique = true, length = 100) // unique 제약조건 추가
    private String userId;
    @NotNull
    @Column(name = "name", length = 20)
    private String name;
    @NotNull
    @Column(name = "password", length = 100)
    private String password;
    @NotNull
    @Email
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "phone_number",length = 20)
    private String phoneNumber;
    @Column(name = "student_id", length = 10)
    private String studentId;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member joinMember(JoinMemberRequest joinMemberRequest){
        Member member = new Member();

        member.userId = joinMemberRequest.getUserId();
        member.name = joinMemberRequest.getName();
        member.password = joinMemberRequest.getPassword();
        member.email = joinMemberRequest.getEmail();
        member.phoneNumber = joinMemberRequest.getPhoneNumber();
        member.studentId = joinMemberRequest.getStudentId();
        member.role = Role.MEMBER;

        return member;

    }
}
