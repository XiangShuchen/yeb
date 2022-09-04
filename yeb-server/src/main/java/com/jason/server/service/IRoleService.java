package com.jason.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.server.pojo.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface IRoleService extends IService<Role> {
    /**
     * 根据用户Id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
