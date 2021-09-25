package com.sec.form.config;

import com.sec.form.handler.CustomLoginFailureHandler;
import com.sec.form.handler.CustomLoginSuccessHandler;
import com.sec.form.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

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
                .anyRequest().authenticated();

        http.rememberMe()
                .key("123456789")
                .tokenValiditySeconds(60 * 60 * 24);

        //h2 DB console 사용을 위해
        http.csrf().disable(); //CSRF 중지
        http.headers().frameOptions().disable(); //X-Frame-Options in Spring Security 중지
    }
}
