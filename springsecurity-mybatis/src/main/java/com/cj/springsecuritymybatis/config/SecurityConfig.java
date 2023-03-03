package com.cj.springsecuritymybatis.config;

import com.cj.springsecuritymybatis.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailsService);
//    }


    /**
     * 注入自己的 AuthenticationProvider
     */
    @Bean
    MyAuthenticationProvider myAuthenticationProvider(){
        MyAuthenticationProvider provider = new MyAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }

    /**
     * 配置自己的 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
       return new ProviderManager(myAuthenticationProvider());

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 写操作 二次校验，，敏感操作
                .antMatchers("/admin/**").fullyAuthenticated()
                .antMatchers("/vf").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/doLogin")
                .successHandler((res,resp,auth)->{
                    resp.setContentType("text/html;charset=utf-8");
                    resp.getWriter().write("success");
                })
                .failureHandler((res,resp,exception)->{
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(exception.getMessage());
                    out.flush();
                    out.close();
                })
                .permitAll()
                .and()
                // 服务器的session 是存在的，，关闭浏览器，，只是 jsessionId不在了,,,, 服务端重启之后，也能够访问
                .rememberMe()
                .key("cc")
                // 持久化 token，，重新登录的时候会重新生成 不一样的 token，防止密码泄露
//                .tokenRepository()
                .and()
                .csrf().disable();
    }
}
