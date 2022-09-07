package com.jason.server.controller;


import com.jason.server.RespBean;
import com.jason.server.pojo.Position;
import com.jason.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/system/cfg/pos")
public class PositionController {

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public RespBean getAllPOPosition(){
        return RespBean.success().obj(positionService.list());
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean savePosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        return positionService.addPosition(position);
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        return positionService.updatePosition(position);
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable String id){
        return positionService.removeById(id)?RespBean.success().message("删除成功"):RespBean.error().message("删除失败");
    }

    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositions(List<Integer> ids){
        return positionService.removeByIds(ids)?RespBean.success().message("删除成功"):RespBean.error().message("删除失败");
    }
}
