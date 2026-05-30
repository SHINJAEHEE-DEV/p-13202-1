package com.back.domain.member.member.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public long count(){
        return memberRepository.count();
    }

    public Member create(String username, String password, String nickname){
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setNickname(nickname);
        memberRepository.save(member);
        return member;
    }
    public Member getMemberByUsername(String username){
        return memberRepository.findByusername(username).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }


}
