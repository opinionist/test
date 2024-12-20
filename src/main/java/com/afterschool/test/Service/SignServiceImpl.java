package com.afterschool.test.Service;

import com.afterschool.test.Entity.User;
import com.afterschool.test.Entity.dto.SignUpResultDto;
import com.afterschool.test.Entity.dto.SigninResultDto;
import com.afterschool.test.jwt.CommonResponse;
import com.afterschool.test.jwt.JwtTokenProvider;
import com.afterschool.test.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService{
    private final Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    public UserRepo userRepo;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(UserRepo userRepo, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto signUp(String id, String password, String name, String role){
        logger.info("[getSignUpResult] 회원 가입 정보 전달");
        User user;
        if(role.equalsIgnoreCase("admin")){
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_ADMIN"))
                    .build();
        }
        else {
            user = User.builder()
                    .uid(id)
                    .name(name)
                    .password(passwordEncoder.encode(password))
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build();
        }

        User savedUser = userRepo.save(user);
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        logger.info("[getSignResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!savedUser.getName().isEmpty()){
            logger.info("[getSignResult] userEntity 정상 처리 완료");

        }
    }

    @Override
    public SigninResultDto signIn(String id, String password) throws RuntimeException {
        return null;
    }

    private void setSuccessResult(SignUpResultDto result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result){
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
