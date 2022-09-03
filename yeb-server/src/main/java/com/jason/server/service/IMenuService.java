package com.jason.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户名Id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();
}
