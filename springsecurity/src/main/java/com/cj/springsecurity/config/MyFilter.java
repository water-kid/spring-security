package com.cj.springsecurity.config;

import com.cj.springsecurity.model.Menu;
import com.cj.springsecurity.model.Role;
import com.cj.springsecurity.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FilterInvocationSecurityMetadataSource : 通过当前的请求地址，获取该地址需要的用户角色
 *
 */
@Component
public class MyFilter  implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    MenuService menuService;

    /**
     *     每次请求都会调用这个方法，，先根据 url地址，算出来角色，，放入ConfigAttribute中
     * @param o the object being secured   可以转换成  FilterInvocation
     *
     *
     *   FilterInvocation:   通过Spring Security 封装，可以安全的拿到HttpServletRequest 和 HttpServletResponse对象
     *
     *   ConfigAttribute： 请求一个资源，所需要的角色都会被封装成一个 ConfigAttribute 对象
     *             # getAttribute() : 返回一个 String字符串就是角色的名称 ROLE_XXX
     *
     * @return 返回这个 访问这个资源需要的 ConfigAttribute
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {

        FilterInvocation filterInvocation = (FilterInvocation) o;
        // 获取 url请求
        String requestUrl = filterInvocation.getRequestUrl();

        List<Menu> allMenus = menuService.getAllMenus();

        for (Menu menu : allMenus) {
            if (pathMatcher.match(menu.getPattern(),requestUrl)){
                List<Role> roleList = menu.getRoleList();
                List<String> roleNameList = roleList.stream().map(Role::getName).collect(Collectors.toList());
                return SecurityConfig.createList(roleNameList.toArray(new String[roleNameList.size()]));
            }
        }

        return SecurityConfig.createList("ROLE_login");

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
