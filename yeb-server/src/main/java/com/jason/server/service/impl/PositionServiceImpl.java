package com.jason.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.server.RespBean;
import com.jason.server.mapper.PositionMapper;
import com.jason.server.pojo.Position;
import com.jason.server.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;

    /**
     * 更新职位
     * @param position
     * @return
     */
    @Override
    public RespBean updatePosition(Position position) {
        if (position!=null) {
            Position checkDate = positionMapper.selectById(position.getId());
            if (checkDate != null) {
                return positionMapper.updateById(position) == 1 ?
                        RespBean.success().message("修改成功") : RespBean.error().message("修改失败");
            } else
                return RespBean.error().message("没有该职位");
        }
        else return RespBean.error();
    }

    /**
     * 插入一个职位
     * @param position
     * @return
     */
    @Override
    public RespBean addPosition(Position position) {
        if (position!=null) {
            Position checkDate = positionMapper.selectById(position.getId());
            if (checkDate ==null) {
                return positionMapper.insert(position)==1 ?RespBean.success().message("保存成功") :RespBean.error().message("保存失败");
            } else {
             return RespBean.error().message("职位名称重复");
            }

        } else return RespBean.error();
    }
}
