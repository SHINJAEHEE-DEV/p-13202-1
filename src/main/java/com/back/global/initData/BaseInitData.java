package com.back.global.initData;

import com.back.domain.article.article.entity.Article;
import com.back.domain.article.article.service.ArticleService;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // final키워드가 붙은 필드를 생성자로 주입
//@Configuration은 스프링 프레임워크에서 Java 기반 구성 클래스를 정의하는 데 사용되는 핵심 어노테이션
//해당 클래스가 하나 이상의 @Bean 메소드를 선언하고 스프링 컨테이너에 의해 처리되어야 함을 나타냄
@Configuration
public class BaseInitData {
    private final ArticleService articleService;
    private final MemberService memberService;

    //@Lazy와 self 주입 쓰는 이유 : 순환참조 문제를 해결하기 위해
    //스프링 컨테이너는 빈을 생성할 때 필요한 의존성을 먼저 주입하려고 시도함.
    // 하지만 BaseInitData가 자기 자신을 주입받으려 하면 논리적 모순에 빠짐.
    // self == 프록시 객체, 프록시객체를 쓰는 이유는 트랜잭션이 가능하게 하기 위해임. this는 불가
    @Lazy //@Lazy는 스프링에서 의존성 주입 시 해당 빈을 실제로 사용할 때까지 초기화를 지연시키는 어노테이션
    @Autowired // @Autowired는 스프링 프레임워크에서 의존성 주입을 수행하는 데 사용되는 어노테이션
    private BaseInitData self;

    // @Bean은 메소드가 스프링 컨테이너에 의해 관리되는 빈을 생성하는 데 사용되는 어노테이션
    @Bean
    //ApplictionRunner는 스프링 부트 애플리케이션이 시작된 후 실행되는 콜백 인터페이스로, 초기화 작업이나 데이터 로딩 등에 사용됨
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {

            self.work1();
        };
    }

    @Transactional// @Transactional은 메소드나 클래스에 적용되어 트랜잭션을 관리하는 데 사용되는 어노테이션
    public void work1() {
        if (memberService.count() > 0) return;
        Member member1 = memberService.create("user1", "password1", "닉네임1");
        Member member2 = memberService.create("user2", "password2", "닉네임2");
        Member member3 = memberService.create("user3", "password3", "닉네임3");

        if (articleService.count() > 0) return;
        Article article1 = new Article("제목 1", "내용 1");
        article1.setAuthor(member1);
        articleService.write(article1);
        Article article2 = new Article("제목 2", "내용 2");
        article2.setAuthor(member2);
        articleService.write(article2);
        Article article3 = new Article("제목 3", "내용 3");
        article3.setAuthor(member3);
        articleService.write(article3);

    }




}
