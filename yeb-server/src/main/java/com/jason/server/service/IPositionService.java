package com.jason.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.server.RespBean;
import com.jason.server.pojo.Position;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface IPositionService extends IService<Position> {

    RespBean updatePosition(Position position);

    RespBean addPosition(Position position);
}
