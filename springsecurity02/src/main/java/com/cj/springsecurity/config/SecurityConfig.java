//package com.cj.springsecurity.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.*;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123").roles("admin")
//                .and()
//                .withUser("cc").password("123").roles("user");
//    }
//
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**","/js/**");
//    }
//
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
//                // 登录成功跳转
////                .successForwardUrl()
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("status",200);
//                        map.put("msg",authentication.getPrincipal());
//                        out.write(new ObjectMapper().writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("status",401);
//                        if (e instanceof LockedException){
//                            map.put("msg","账户被锁定");
//                        }else if (e instanceof BadCredentialsException){
//                            map.put("msg","用户名或者密码错误");
//                        } else if (e instanceof DisabledException) {
//                            map.put("msg","账户禁用");
//                        } else if (e instanceof AccountExpiredException) {
//                            map.put("msg","账户过期");
//                        } else if (e instanceof CredentialsExpiredException) {
//                            map.put("msg","密码过期");
//                        }else {
//                            map.put("msg","other error");
//                        }
//                        out.write(new ObjectMapper().writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("status",200);
//                        map.put("msg","注销登录成功");
//                        out.write(new ObjectMapper().writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .and()
//                .csrf().disable();
//    }
//}
