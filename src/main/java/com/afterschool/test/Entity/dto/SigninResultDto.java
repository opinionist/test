package com.afterschool.test.Entity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SigninResultDto extends SignUpResultDto{
    private String token;
    private String refresh;

    @Builder
    public SigninResultDto(boolean success, int code, String msg, String token, String refresh) {
        super(success, code, msg);
        this.token = token;
        this.refresh = refresh;
    }
}
