package com.back.domain.member;

import lombok.Getter;

//스프링 시큐리티는 인증뿐만 아니라 권한도 관리한다.
// 스프링 시큐리티는 사용자 인증 후에 사용자에게 부여할 권한과 관련된 내용이 필요하다.
@Getter
public enum MemberRole {
    //enum 은 열거형으로, 고정된 상수 집합을 정의하는 데 사용된다.
    // MemberRole은 사용자 역할을 나타내는 열거형이다.
    //  ADMIN과 USER라는 두 가지 역할을 정의하고 있다.
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");;

    MemberRole(String value) {
        this.value = value;
    }
    private String value;
}