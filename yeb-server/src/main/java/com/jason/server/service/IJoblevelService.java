package com.jason.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.server.RespBean;
import com.jason.server.pojo.Joblevel;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
public interface IJoblevelService extends IService<Joblevel> {

    RespBean addJobLevel(Joblevel joblevel);

    RespBean updateJobLevel(Joblevel joblevel);

}
