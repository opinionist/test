package com.afterschool.test.Entity;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;

    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password).
                build();
    }
}
