package com.sec.form.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationEventListener {

    //인증이벤트
    @EventListener
    public void handleAuthenticationEvent(AbstractAuthenticationEvent event) {
        log.info("handleAuthenticationEvent= {}", event);
    }

    //잘목된 입력정보로 인한 인증 실패
    @EventListener
    public void handleBadCredentials(AuthenticationFailureBadCredentialsEvent event) {
        log.info("handleBadCredentials= {}", event);
    }

    //비활성 계정으로 인한 인증 실패 이벤트
    @EventListener
    public void handleFailureDisabledEvent(AuthenticationFailureDisabledEvent event) {
        log.info("handleFailureDisabledEvent= {}", event);
    }

    //잠긴 계정으로 인한 인증 실패 이벤트
    @EventListener
    public void handleFailureLockedEvent(AuthenticationFailureLockedEvent event) {
        log.info("handleFailureLockedEvent= {}", event);
    }

    //계정 만료로 인한 인증 실패 이벤트
    @EventListener
    public void handleFailureExpiredEvent(AuthenticationFailureExpiredEvent event) {
        log.info("handleFailureExpiredEvent= {}", event);
    }

    //자격 증명 완료로 인한 인증 실패 이벤트
    @EventListener
    public void handleFailureCredentialsExpiredEvent(AuthenticationFailureCredentialsExpiredEvent event) {
        log.info("handleFailureCredentialsExpiredEvent= {}", event);
    }

    //AuthenticationManager 내부 문제로 인한 인증 실패 이벤트
    @EventListener
    public void handleFailureServiceExceptionEvent(AuthenticationFailureServiceExceptionEvent event) {
        log.info("handleFailureServiceExceptionEvent= {}", event);
    }

    //인증성공
    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        log.info("handleAuthenticationSuccess= {}", event);
    }

    //세션 고정 보호->세션id가 변경됨을 나타냄
    @EventListener
    public void handleSessionFixationProtectionEvent(SessionFixationProtectionEvent event) {
        log.info("handleSessionFixationProtectionEvent= {}", event);
    }

    //대화형 인증 성공
    @EventListener
    public void handleInteractiveSuccessEvent(InteractiveAuthenticationSuccessEvent event) {
        log.info("handleInteractiveSuccessEvent= {}", event);
    }

    //로그아웃 이벤트
    @EventListener
    public void handleLogoutSuccessEvent(LogoutSuccessEvent event) {
        log.info("handleLogoutSuccessEvent= {}", event);
    }
}
