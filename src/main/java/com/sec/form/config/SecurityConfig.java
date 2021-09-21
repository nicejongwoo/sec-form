package com.sec.form.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123").roles("MEMBER")
//                .and()
//                .withUser("manager").password("123").roles("MEMBER", "ADMIN")
//                .and()
//                .withUser("admin").password("123").roles("ADMIN");
//    }

//    //UserDetailsService 재정의
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        UserDetails user = users.username("user")
//                .password("123")
//                .roles("MEMBER")
//                .build();
//
//        UserDetails manager = users.username("manager")
//                .password("123")
//                .roles("MEMBER", "ADMIN")
//                .build();
//
//        UserDetails admin = users.username("admin")
//                .password("123")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, manager, admin);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
        http.formLogin();
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .anyRequest()
                .authenticated();
    }
}
