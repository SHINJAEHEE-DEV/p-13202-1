package com.back.domain.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String main() {
        return "redirect:/article/list"; //루트로 진입시 리스트페이지로 리다이렉트
    }
}
