package com.example.schedulerv2.filter;

import com.example.schedulerv2.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class JwtAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);

        // 로그인, 회원가입은 인증 통과
        String requestURI = httpServletRequest.getRequestURI();

        if (requestURI.equals("/users/signup") || requestURI.equals("/users/login")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null) {
            // 세션이 없으면 인증되지 않은 상태이므로 401 반환
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Unauthorized - No session found");
            return; // 응답 종료
        }

        // 세션이 있으면 JWT 토큰을 확인하고 이메일을 요청 속성에 저장
        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken != null && JwtUtil.validateToken(jwtToken)) {
            String email = JwtUtil.getEmailFromJwt(jwtToken);
            request.setAttribute("email", email);
        } else {
            // 토큰이 없거나 유효하지 않으면 인증 실패 처리
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Unauthorized - Invalid token");
            return; // 응답 종료
        }

        chain.doFilter(request, response);
    }
}
