package com.jason.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.server.mapper.MenuMapper;
import com.jason.server.pojo.Admin;
import com.jason.server.pojo.Menu;
import com.jason.server.service.IMenuService;
import com.jason.server.utils.Const;
import com.jason.server.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 根据用户名Id获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getId();
        // 从redis 中获取menus 如果没有就查询出来，然后添加到redis中去
        List<Menu> menus = (List<Menu>) redisUtils.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(adminId);
            redisUtils.set("menu_" + adminId, true);
        }
        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        List<Menu> menuList= (List<Menu>) redisUtils.get(Const.ROLE_MENU);
        if (CollectionUtils.isEmpty(menuList)) {
            menuList=  menuMapper.getMenusWithRole();
            redisUtils.set(Const.ROLE_MENU,menuList,true);
        }
        return menuList;
    }
}
