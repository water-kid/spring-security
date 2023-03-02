//package com.cj.springsecurity.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
//public class MultiHttpSecurityConfig {
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("$2a$10$m3I0M7bs/6DBA5CALmR58OhoiaIaah0YqqEC.Y/RlLy9MKkfpBDnG").roles("admin")
//                .and()
//                .withUser("cc").password("$2a$10$m3I0M7bs/6DBA5CALmR58OhoiaIaah0YqqEC.Y/RlLy9MKkfpBDnG").roles("user");
//    }
//
//    @Configuration
//    @Order(1) // 数字越小越优先
//    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
////            http.authorizeRequests()
//
//            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//                    .loginProcessingUrl("/doLogin")
//                    .permitAll()
//                    .and()
//                    .csrf().disable();
//        }
//    }
//}
