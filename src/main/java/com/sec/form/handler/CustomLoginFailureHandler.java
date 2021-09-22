package com.sec.form.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("onAuthenticationFailure");
        log.error("onAuthenticationFailure exception= {}", e.getMessage());

        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
        String redirectUrl = savedRequest.getRedirectUrl();
        log.info("redirectUrl= {}", redirectUrl);
        httpServletResponse.sendRedirect(redirectUrl);
    }
}
