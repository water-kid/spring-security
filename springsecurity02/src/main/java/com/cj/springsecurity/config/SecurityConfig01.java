package com.cj.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 前后端交互
 */
//@Configuration
public class SecurityConfig01 extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("admin")
                .and()
                .withUser("cc").password("123").roles("user");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**");
    }

//    /**
//     * 前后端不分离
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 配置拦截规则
//        // 开启认证
//        http.authorizeRequests().antMatchers("/admin/**").hasRole("admin")
//                .antMatchers("/user/**").access("hasAnyRole('user','admin')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/doLogin")
//                .loginPage("/login.html")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                // 登录成功跳转 服务端跳转  因为 doLogin 是post方式发送的，那么请求转发也是 post请求转发到这个地址 ，，转发的接口写成post
////                .successForwardUrl("/index")
//                // 重定向
//                .defaultSuccessUrl("/s",false)
//                // 服务端跳转  跳转会延续上一次的请求方式
////                .failureForwardUrl("/err")
//                .failureUrl("/err")
//                .permitAll()
//                .and()
//                .logout()
//                // 注销地址，默认get
////                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
//                .logoutSuccessUrl("/login.html")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .permitAll()
//                .and()
//                .csrf().disable();
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .successHandler((req,resp,authentication)->{
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("code",200);
//                    map.put("msg",authentication.getPrincipal());
//                    out.write(new ObjectMapper().writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
////                .failureHandler()
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessHandler((req,resp,authentication)->{
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("code",200);
//                    map.put("msg","注销登录成功");
//                    out.write(new ObjectMapper().writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((req,resp,exception)->{
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("code",401);
//                    map.put("msg","尚未登录，请登录");
//                    out.write(new ObjectMapper().writeValueAsString(map));
//                    out.flush();
//                    out.close();
//                });
//    }
}
