package com.jason.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.server.RespBean;
import com.jason.server.mapper.JoblevelMapper;
import com.jason.server.pojo.Joblevel;
import com.jason.server.service.IJoblevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Autowired
    private JoblevelMapper joblevelMapper;

    /**
     * 添加职称
     *
     * @param joblevel
     * @return
     */
    @Override
    public RespBean addJobLevel(Joblevel joblevel) {
        if (joblevel.getName() != null) {
            List<Joblevel> checkDate = joblevelMapper.selectList(new QueryWrapper<Joblevel>().eq("name", joblevel.getName()));
            if ( checkDate.size() >0) {
                return RespBean.error().message("职称名称重复");
            }
        }
        joblevel.setCreateDate(LocalDateTime.now());

        return joblevelMapper.insert(joblevel) >= 0 ? RespBean.success().message("添加成功") : RespBean.error().message("添加失败");
    }

    /**
     * 更新职称
     * @param joblevel
     * @return
     */
    @Override
    public RespBean updateJobLevel(Joblevel joblevel) {
        if (joblevel.getName() != null) {
            List<Joblevel> checkDate = joblevelMapper.selectList(new QueryWrapper<Joblevel>().eq("name", joblevel.getName()));
            if (checkDate.size()> 0) {
                return RespBean.error().message("职称名称重复");
            }
        }
        return joblevelMapper.updateById(joblevel) >= 0 ? RespBean.success().message("添加成功") : RespBean.error().message("添加失败");
    }
}
