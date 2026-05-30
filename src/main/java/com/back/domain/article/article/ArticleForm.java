package com.back.domain.article.article;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleForm {
    @NotEmpty(message = "제목은 필수입니다.")
    @Size(min = 3, message = "제목은 최소 3자 이상이어야 합니다.")
    String title;
    @NotEmpty(message = "내용은 필수입니다.")
    @Size(min = 3, message = "내용은 최소 3자 이상이어야 합니다.")
    String content;
}
