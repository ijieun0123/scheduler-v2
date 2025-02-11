package com.example.schedulerv2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    // JWT 생성
    static public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // JWT 검증
    static public boolean validateToken(String jwtToken){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY) // 안전한 Secret Key 사용
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // JWT 에서 이메일 추출
    static public String getEmailFromJwt(String jwtToken){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        return claims.getSubject();
    }

    // request 에서 email 추출
    static public String getEmailFromRequest(HttpServletRequest request){
        String email = (String) request.getAttribute("email");

        if(email == null) throw new IllegalArgumentException("리퀘스트에 이메일이 없습니다.");

        return email;
    }
}
