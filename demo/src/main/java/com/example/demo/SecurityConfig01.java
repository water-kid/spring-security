package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置拦截规则
        // 开启认证
        http.authorizeRequests().antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").access("hasAnyRole('user','admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
//                .loginPage("/login.html")
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功跳转 服务端跳转  因为 doLogin 是post方式发送的，那么请求转发也是 post请求转发到这个地址 ，，转发的接口写成post
//                .successForwardUrl("/index")
                // 重定向
                .defaultSuccessUrl("/s",false)
                // 服务端跳转
//                .failureForwardUrl("/login.html")
//                .failureUrl("/login.html")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable();
    }
}
