package com.afterschool.test.Entity.dto;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInCauseDto {
    @NonNull
    private String id;

    @NonNull
    private String password;
}
