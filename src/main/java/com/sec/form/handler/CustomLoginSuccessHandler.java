package com.sec.form.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User customUser = (User) authentication.getPrincipal();
        String username = customUser.getUsername();
        log.info("user 아이디= {}", username);

        //로그인 세션 지우기
        clearAuthenticationAttribute(httpServletRequest);

        //이동할 url 지정
        resultRedirectStrategy(httpServletRequest, httpServletResponse);

    }

    private void resultRedirectStrategy(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
        String targetUrl = savedRequest.getRedirectUrl();
        log.info("redirectUrl= {}", targetUrl);
        httpServletResponse.sendRedirect(targetUrl);
    }

    private void clearAuthenticationAttribute(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
