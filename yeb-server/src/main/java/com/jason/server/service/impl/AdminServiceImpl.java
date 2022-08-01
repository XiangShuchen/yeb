package com.jason.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.server.config.security.IUserDetailsService;
import com.jason.server.config.security.JwtTokenUtil;
import com.jason.server.mapper.AdminMapper;
import com.jason.server.pojo.Admin;
import com.jason.server.pojo.DTO.BaseDTO.RespBean;
import com.jason.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private IUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error().message("用户名密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error().message("该账户被禁用");
        }
        //更新 登录对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功").obj(tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>()
                .eq("username", username)
                .eq("enabled", true));

    }
}