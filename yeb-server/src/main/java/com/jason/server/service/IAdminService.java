package com.jason.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.server.pojo.Admin;
import com.jason.server.pojo.DTO.BaseDTO.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUsername(String username);
}
