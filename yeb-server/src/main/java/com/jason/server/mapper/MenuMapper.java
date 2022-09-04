package com.jason.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户名Id获取菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
