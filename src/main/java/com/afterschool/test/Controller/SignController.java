package com.afterschool.test.Controller;

import com.afterschool.test.Entity.dto.SignInCauseDto;
import com.afterschool.test.Entity.dto.SignUpCauseDto;
import com.afterschool.test.Entity.dto.SignUpResultDto;
import com.afterschool.test.Entity.dto.SigninResultDto;
import com.afterschool.test.Service.SignService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sign-api")
public class SignController {
    private final Logger logger = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @Autowired
    public SignController (SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SigninResultDto> signIn(@RequestBody SignInCauseDto signInCauseDto, HttpServletResponse response) throws RuntimeException {
        String id = signInCauseDto.getId();
        String password = signInCauseDto.getPassword();
        HttpHeaders headers = new HttpHeaders();

        logger.info("[SignIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
        SigninResultDto signInResultDto = signService.signIn(id, password, response);

        if (signInResultDto.getCode() == 0){
            logger.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", id, signInResultDto.getToken());
            headers.set("token", signInResultDto.getToken());
        }
        return ResponseEntity.ok().headers(headers).body(signInResultDto);
    }

    @PostMapping("/sign-up")
    public SignUpResultDto signUp(@RequestBody SignUpCauseDto signUpCauseDto){
        String id = signUpCauseDto.getId();
        String password = signUpCauseDto.getPassword();
        String name = signUpCauseDto.getName();
        String role = signUpCauseDto.getRole();
        logger.info("[SignUp] 회원가입을 수행합니다. id : {}", id);

        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);

        logger.info("[SignUp] 회원가입을 완료했습니다. id : {}", id);
        return signUpResultDto;
    }

    @GetMapping("/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(RuntimeException e){
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        logger.error("ExceptionHandler 호출, {}, {}", e.getCause(),e.getMessage());

        Map<String,String> map = new HashMap<>();

        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
