package com.example.schedulerv2.filter;

import com.example.schedulerv2.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtAuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = ((HttpServletRequest) request).getRequestURI();

        // 0. 회원가입과 로그인 경로는 인증을 거치지 않도록 예외 처리
        if (requestURI.contains("/users/signup") || requestURI.contains("/users/login")) {
            chain.doFilter(request, response);
            return;
        }

        // 1. 요청 헤더에서 Authorization 값 가져오기
        String authHeader = httpServletRequest.getHeader("Authorization");

        // 2. 토큰이 없거나 "Bearer" 로 시작하지 않으면 401 에러
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
            return;
        }

        // 3. "Bearer " 제거 후 토큰만 추출
        String token = authHeader.substring(7);

        try{
            // 4. 토큰 검증
            Claims claims = JwtUtil.validateToken(token);
            request.setAttribute("email", claims.getSubject()); // 사용자 정보 저장
        } catch (Exception e){
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        // 5. 필터 체인 계속 진행
        chain.doFilter(request, response);
    }
}
