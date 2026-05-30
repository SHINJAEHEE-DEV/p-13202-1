package com.back.domain.member.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {
    @NotEmpty(message = "아이디는 필수입니다.")
    private String username;
    @NotEmpty(message = "비밀번호는 필수입니다.")
    private String password;
    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String passwordConfirm;
    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;
}
