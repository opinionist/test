package com.afterschool.test.Controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Value("${jwt.secret}")
    public String secretKey;

    @PostMapping("/test")
    public ResponseEntity<?> PostTest(@RequestHeader("X-AUTH-TOKEN") String authorizationHeader){
        String token = authorizationHeader.replace("Bearer ", "");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token).getBody();

        String username = claims.getSubject();

        return ResponseEntity.ok().body(username);
    }
}
