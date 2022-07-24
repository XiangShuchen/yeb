package com.jason.server.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jason.server.mapper.AdminMapper;
import com.jason.server.pojo.Admin;
import com.jason.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class IUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminMapper AdminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AdminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username));

    }
}
