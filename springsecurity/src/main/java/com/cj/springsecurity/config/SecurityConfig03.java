package com.cj.springsecurity.config;

import com.cj.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig03  extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userService;

    @Autowired
    MyFilter myFilter;

    @Autowired
    MyAccessDecisionManager myAccessDecisionManager;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * ObjectPostProcessor:
         *  实现类 ：AutowireBeanFactoryObjectPostProcessor
         *         通过postProcess方法手把Bean注入到spring容器进行管理。
         *
         * FilterSecurityInterceptor :
         *          作为 Spring Security Filter Chain 的最后一个 Filter，承担着非常重要的作用。
         *          如获取当前 request 对应的权限配置，调用访问控制器进行鉴权操作等，都是核心功能。
         */
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                // 将  AccessDecisionManager ，FilterInvocationSecurityMetadataSource 放入Filter中
                object.setAccessDecisionManager(myAccessDecisionManager);
                object.setSecurityMetadataSource(myFilter);
                return object;
            }
        }
        ).and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();
    }
}
