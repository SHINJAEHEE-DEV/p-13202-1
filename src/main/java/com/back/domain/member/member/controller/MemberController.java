package com.back.domain.member.member.controller;

import com.back.domain.member.member.MemberCreateForm;
import com.back.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String login(){
        return "/member/member/login";
    }

    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm){
        return "/member/member/signup";
    }



    @PostMapping("/signup")
    public String signupPost(
            @Valid MemberCreateForm memberCreateForm,
            BindingResult bindingResult
    ){

        //BindingResult는 @Valid로 검증한 결과를 담는 객체입니다.
        // 검증에 실패한 경우, bindingResult.hasErrors()는 true를 반환합니다.
        if(bindingResult.hasErrors()){
            return "/member/member/signup";
        }
        if(!memberCreateForm.getPassword()
                .equals(memberCreateForm.getPasswordConfirm())){
            bindingResult.rejectValue("passwordConfirm", "passwordInCorrect",
                    "비밀번호가 일치하지 않습니다.");
            return "/member/member/signup";
        }
        //중복검사
        try {
           memberService.create(
                   memberCreateForm.getUsername(),
                   memberCreateForm.getPassword(),
                   memberCreateForm.getNickname()
           );
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "/member/member/signup";
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "/member/member/signup";
        }

        return "redirect:/";
    }

}
