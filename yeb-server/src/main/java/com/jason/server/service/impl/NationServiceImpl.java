package com.jason.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.server.mapper.NationMapper;
import com.jason.server.pojo.Nation;
import com.jason.server.service.INationService;
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
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
