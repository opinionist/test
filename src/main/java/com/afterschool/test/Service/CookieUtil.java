package com.afterschool.test.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CookieUtil {
    Logger logger = LoggerFactory.getLogger(CookieUtil.class);
    public void addJwtCookie(HttpServletResponse response, String accessToken, String refreshToken){
        Cookie access = new Cookie("access", accessToken);
        access.setPath("/");
        access.setHttpOnly(true);
        access.setSecure(true);
        access.setMaxAge(7*24*60*60);
        response.addCookie(access);

        Cookie refresh = new Cookie("refresh", refreshToken);
        refresh.setPath("/");
        refresh.setHttpOnly(true);
        refresh.setSecure(true);
        refresh.setMaxAge(7*24*60*60);
        response.addCookie(refresh);
    }

    public String getJwtCookie(HttpServletRequest request){
        if(request.getCookies() != null){
            logger.info("[CookieUtil] getJwtCookie");
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("access")){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
