package com.afterschool.test.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthenticationFilter2 extends GenericFilterBean {
    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter2.class);
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter2(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
        logger.info("[doFilterInternal] token 값 추출 완료. token: {}", token);

        logger.info("[doFilterInternal] token 값 유효성  체크 시작");
        if (token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("[doFilterInternal] token 값 유효성  체크 완료");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
