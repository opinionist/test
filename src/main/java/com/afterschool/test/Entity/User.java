package com.afterschool.test.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Table
@Entity
@RequiredArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;

    private String email;

    @Builder
    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
}
