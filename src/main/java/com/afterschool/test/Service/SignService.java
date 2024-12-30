package com.afterschool.test.Service;

import com.afterschool.test.Entity.dto.SignUpResultDto;
import com.afterschool.test.Entity.dto.SigninResultDto;
import jakarta.servlet.http.HttpServletResponse;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);

    SigninResultDto signIn(String id, String password, HttpServletResponse response) throws RuntimeException;
}
