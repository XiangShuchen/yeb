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
    List<Menu> getMenusByAdminId(Integer id);
}
