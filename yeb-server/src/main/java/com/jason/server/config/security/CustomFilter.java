package com.jason.server.config.security;

import com.jason.server.pojo.Menu;
import com.jason.server.pojo.Role;
import com.jason.server.service.IMenuService;
import com.jason.server.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @author jason.Xiang
 * @description : 根据请求url分析请求所需的角色
 * @since 2022/9/4-17
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private IMenuService menuService;

    private final AntPathMatcher antPathMatcher =new AntPathMatcher();



    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menusWithRole = menuService.getMenusWithRole();
        for (Menu menu : menusWithRole) {
            // 判断请求的URl与菜单角色是否匹配
            if (antPathMatcher.match(menu.getUrl(),requestUrl)) {
                String[] roles = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(roles);
            }
            if (Const.URL_LIST.stream().anyMatch(s -> antPathMatcher.match(s,requestUrl)))
            {
                return SecurityConfig.createList(Const.RESOURCES_URL);
            }
        }
        //如果没有匹配上 默认登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
