package com.back.domain.article.article.entity;

import com.back.domain.member.member.entity.Member;
import com.back.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article extends BaseEntity {

    private String title;
    private String content;
    @ManyToOne
    private Member author;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
    //만든이유 : 더티체킹 (Dirty Checking) : JPA에서 엔티티의 상태를 감지하여 변경된 필드를 DB에 자동으로 업데이트하는 기능
    public void modify(String title, String content){
        this.title = title;
        this.content = content;
    }

}
