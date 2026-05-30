package com.back.global.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
// MappedSuperclass는"이 클래스는 단독으로 DB 테이블이 되지는 않지만,
// 나를 상속받는 자식 엔티티들에게 내 필드(컬럼)들을 물려주겠다"고 선언하는 어노테이션
@MappedSuperclass
// EntityListeners는 "이 엔티티에 데이터가 저장되거나 수정되는 이벤트(상태 변화)가 발생할 때,
// AuditingEntityListener가 감시하고 있다가 자동으로 시간을 기록해 주겠다"고 선언하는 어노테이션
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @CreatedDate
    LocalDateTime createDate;
    @LastModifiedDate
    LocalDateTime modifyDate;
}

