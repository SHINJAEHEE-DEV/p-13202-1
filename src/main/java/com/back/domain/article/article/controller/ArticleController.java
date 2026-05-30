package com.back.domain.article.article.controller;

import com.back.domain.article.article.ArticleForm;
import com.back.domain.article.article.entity.Article;
import com.back.domain.article.article.service.ArticleService;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

        @GetMapping("/list")
        public String list(Model model) {
            model.addAttribute("articles", articleService.getList());
            return "article/article/list";
        }
        @GetMapping("/create")
        public String create(ArticleForm articleForm, Principal principal) {
            if(principal == null){
                return "redirect:/members/login";
            }
            return "article/article/create";
        }


        @Transactional
        @PostMapping("/create")
        public String createArticle(@Valid ArticleForm aticleForm, Principal principal) {
            Member member = memberService.getMemberByUsername(principal.getName());
            Article article = new Article(aticleForm.getTitle(), aticleForm.getContent());
            article.setAuthor(member);
            articleService.write(article);
            return "redirect:/article/detail/%d".formatted(article.getId());
        }

        @GetMapping("/detail/{id}")
        public String detail(Model model, @PathVariable("id") long id) {
            Article article = articleService.getArticleById(id);
            model.addAttribute("article", article);
            return "article/article/detail";
        }

        @GetMapping("/modify/{id}")
        public String modify(Model model, @PathVariable("id") long id) {
            Article article = articleService.getArticleById(id);
            model.addAttribute("article", article);
            return "article/article/modify";
        }
        @Transactional
        @PostMapping("/modify/{id}")
        public String modify(
                @PathVariable("id") long id,
                @Valid ArticleForm articleForm,
                Principal principal // Principal은 현재 로그인한 사용자의 정보를 담고 있는 객체
        ) {
            Article article = articleService.getArticleById(id);

            if(!article.getAuthor().getUsername().equals(principal.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
            }
            //더티체킹으로 entity 수정시 바로 DB에 반영됨
            article.modify(articleForm.getTitle(), articleForm.getContent());
            return "redirect:/article/detail/%d".formatted(article.getId());
        }
        @Transactional
        @PostMapping("/delete/{id}")
        public String delete(@PathVariable("id") long id, Principal principal) {

            Article article = articleService.getArticleById(id);

            if(!article.getAuthor().getUsername().equals(principal.getName())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
            }
            articleService.delete(article);
            return "redirect:/article/list";
        }


}
