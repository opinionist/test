package com.afterschool.test.Entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SigninResultDto extends SignUpResultDto{
    private String token;

    @Builder
    public SigninResultDto(boolean success, int code, String msg, String token) {
        super(success, code, msg);
        this.token = token;
    }
}
