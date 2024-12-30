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
    public void addJwtCookie(HttpServletResponse response, String token){
        Cookie cookie = new Cookie("access", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
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
