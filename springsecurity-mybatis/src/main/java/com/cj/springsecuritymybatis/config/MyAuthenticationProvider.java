package com.cj.springsecuritymybatis.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class MyAuthenticationProvider  extends DaoAuthenticationProvider {
    /**
     * 校验密码的方法
     *
     * 在校验 密码之前，校验验证码
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String code = request.getParameter("code");
        String vf = (String) request.getSession().getAttribute("vf");
        if (code == null || vf==null || !vf.equals(code)){
            throw new AuthenticationServiceException("验证码错误");
        }
        // 校验密码
        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
