package com.afterschool.test.Service;

import com.afterschool.test.Entity.dto.SignUpResultDto;
import com.afterschool.test.Entity.dto.SigninResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);

    SigninResultDto signIn(String id, String password) throws RuntimeException;
}
