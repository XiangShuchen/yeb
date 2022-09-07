package com.jason.server.config.security;

import com.jason.server.utils.Const;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author jason.Xiang
 * @description : 判断用户角色
 * @since 2022/9/4-17
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {


    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            //获取当前URL所需角色
            String needRole = configAttribute.getAttribute();
            // 判断角色是否登录即可访问的角色，此角色在CustomFilter 中设置
            if ("ROLE_LOGIN".equals(needRole) && authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请重新登录");
            } else if (Const.RESOURCES_URL.equals(needRole)) {
                return;
            } else {
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(needRole)) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
