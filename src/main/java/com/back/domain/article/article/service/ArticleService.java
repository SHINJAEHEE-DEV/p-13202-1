package com.back.domain.article.article.service;

import com.back.domain.article.article.entity.Article;
import com.back.domain.article.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void write(Article article) {
        articleRepository.save(article);
    }

    public long count(){
        return articleRepository.count();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException("게시글이 존재하지 않습니다.")
        );
    }

    public List<Article> getList() {
        return articleRepository.findAll();
    }

     public void delete(Article article) {
        articleRepository.delete(article);

    }



}
