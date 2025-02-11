package com.example.schedule.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SessionValidationFilter implements Filter {
    //회원가입 및 로그인 요청은 필터를 통과
    //그 외의 요청은 세션 검사 후 인증되지 않으면 401응답을 한다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI();

        //회원가입과 로그인 요청을 필터에서 제외
        if(path.startsWith("/users/signup") || path.startsWith("/users/register") || path.startsWith("/users/login") || path.startsWith("/users/logout")) {
            chain.doFilter(request, response);
            return;
        }

        if(session == null || session.getAttribute("userId") == null) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        chain.doFilter(request, response);
    }
}
