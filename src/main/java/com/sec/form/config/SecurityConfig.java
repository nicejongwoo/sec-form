package com.sec.form.config;

import com.sec.form.common.security.handler.CustomAccessDeniedHandler;
import com.sec.form.common.security.handler.CustomAuthenticationEntryPoint;
import com.sec.form.common.security.service.CustomUserDetailsService;
import com.sec.form.handler.CustomLoginFailureHandler;
import com.sec.form.handler.CustomLoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
        http.formLogin()
                .loginPage("/auth/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler());

        http.logout()
                .logoutUrl("/auth/logout") //form의 action과 일치
                .logoutSuccessUrl("/") //로그아웃 성공 후 홈으로 이동
                .permitAll();

        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/node_modules/**", "/h2-console/**").permitAll()
                .antMatchers("/", "/auth/login").permitAll()
                .antMatchers("/user/register", "/user/registerSuccess").permitAll()
                .antMatchers("/codegroup/**").hasRole("ADMIN")
                .antMatchers("/codedetail/**").hasRole("ADMIN")
                .antMatchers("/board/list", "/board/read").permitAll()
                .antMatchers("/board/register", "/board/modify").hasRole("MEMBER")
                .antMatchers("/board/remove").hasAnyRole("MEMBER", "ADMIN")
                .antMatchers("/notice/list", "/notice/read").permitAll()
                .antMatchers("/notice/register", "/notice/modify", "/notice/remove").hasRole("ADMIN")
                .anyRequest().authenticated();

//        http.exceptionHandling().accessDeniedPage("/error/accessError");

        http.exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //사용자인증 엔트리포인트 객체 지정
                .accessDeniedHandler(accessDeniedHandler());

        http.rememberMe()
                .userDetailsService(customUserDetailsService())
                .tokenRepository(createJdbcRepository())
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(60 * 60 * 24);

        //h2 DB console 사용을 위해
//        http.csrf().disable(); //CSRF 중지
        http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("!/h2-console/**"));
        http.headers().frameOptions().disable(); //X-Frame-Options in Spring Security 중지
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    private PersistentTokenRepository createJdbcRepository() {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        return repository;
    }
}
