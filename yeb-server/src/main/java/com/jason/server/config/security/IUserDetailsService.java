package com.jason.server.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.server.mapper.AdminMapper;
import com.jason.server.mapper.RoleMapper;
import com.jason.server.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminMapper AdminMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username){
        Admin admin = AdminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));
        if (admin != null) {
            admin.setRoles(roleMapper.getRoles(admin.getId()));
            return admin;
        }
        throw  new UsernameNotFoundException("用户名密码不正确");
    }
}
