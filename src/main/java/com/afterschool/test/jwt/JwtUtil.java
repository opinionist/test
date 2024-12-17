//package com.afterschool.test.jwt;
//
//import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String secretKey;
//    private final Long access;
//    private final Long refresh;
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis()))
//    }
//}
