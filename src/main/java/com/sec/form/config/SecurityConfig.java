package com.sec.form.config;

import com.sec.form.handler.CustomLoginFailureHandler;
import com.sec.form.handler.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
        String query1 = "SELECT user_id, user_pw, enabled FROM member WHERE user_id = ?";
        String query2 = "SELECT m.user_id, a.auth FROM member_auth AS a, member AS m WHERE a.user_no = m.user_no AND m.user_id = ?";
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(query1)
                .authoritiesByUsernameQuery(query2)
                .passwordEncoder(passwordEncoder());
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
                .anyRequest().authenticated();

        //h2 DB console 사용을 위해
        http.csrf().disable(); //CSRF 중지
        http.headers().frameOptions().disable(); //X-Frame-Options in Spring Security 중지
    }
}
