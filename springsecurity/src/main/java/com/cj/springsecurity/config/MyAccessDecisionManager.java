package com.cj.springsecurity.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class MyAccessDecisionManager  implements AccessDecisionManager {
    /**
     * 比较用户所具备的角色   和 ConfigAttribute 的关系
     * @configAttributes ： FilterInvocationSecurityMetadataSource返回的 ConfigAttribute
     * 抛异常，终止，就是不通过
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        boolean isFlag = false;
        for (ConfigAttribute configAttribute : collection) {
            if ("ROLE_login".equals(configAttribute.getAttribute())){
                // 默认 ROLE_login 登录就可以访问
                if (authentication instanceof AnonymousAuthenticationToken){
                    // 匿名用户
                    throw new AccessDeniedException("非法请求");
                }else{
                    // 已经登录
                    break;
                }
            }
            //
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(configAttribute.getAttribute())){
                    // 具备权限    ： 判断策略：具备其中一个
                    isFlag = true;
                    break;
                }
            }
        }

        if (!isFlag){
            throw new AccessDeniedException("没有权限");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
