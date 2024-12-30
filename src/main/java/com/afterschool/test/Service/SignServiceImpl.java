package com.afterschool.test.Service;

import com.afterschool.test.Entity.User;
import com.afterschool.test.Entity.dto.SignUpResultDto;
import com.afterschool.test.Entity.dto.SigninResultDto;
import com.afterschool.test.jwt.CommonResponse;
import com.afterschool.test.jwt.JwtTokenProvider;
import com.afterschool.test.repository.UserRepo;
import jakarta.servlet.http.HttpServletResponse;
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
    public CookieUtil cookieUtil;

    @Autowired
    public SignServiceImpl(UserRepo userRepo, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, CookieUtil cookieUtil) {
        this.userRepo = userRepo;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.cookieUtil = cookieUtil;
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
            setSuccessResult(signUpResultDto);
        }
        else{
            logger.info("[getSignResult] userEntity 실패 처리 완료");
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public SigninResultDto signIn(String id, String password, HttpServletResponse response) throws RuntimeException {
        logger.info("[getSignInResult] signDataHandler 로 회원 정보 요청");
        User user = userRepo.getByUid(id);
        logger.info("[getSignInResult] 패스워드 비교 수행");

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException();
        }
        logger.info("[getSignInResult] 패스워드 일치");

        logger.info("[getSignInResult] SignInResultDto 객체 생성");
        SigninResultDto signinResultDto = SigninResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(user.getUid())
                ,user.getRoles()))
                .build();

        cookieUtil.addJwtCookie(response,signinResultDto.getToken());
        logger.info("[getSignInResult] Cookie에 token값 주입");
        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");
        setSuccessResult(signinResultDto);

        return signinResultDto;
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
