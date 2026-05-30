package com.back.domain.member.member.entity;

import com.back.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    private String password;
    private String nickname;


}
