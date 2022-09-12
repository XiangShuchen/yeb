package com.jason.server.controller;


import com.jason.server.RespBean;
import com.jason.server.pojo.Joblevel;
import com.jason.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService service;

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/")
    public RespBean getAllJobLevels(){
        return RespBean.success().obj(service.list());
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel){
        return  service.addJobLevel(joblevel);
    }

    @ApiOperation(value = "更新职称")
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel){
        return  service.updateJobLevel(joblevel);
    }

    @ApiOperation(value = "删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        return  service.removeById(id) ? RespBean.success().message("删除成功") : RespBean.error().message("删除失败");
    }


    @ApiOperation(value = "批量删除职称")
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        return  service.removeByIds(Arrays.asList(ids)) ? RespBean.success().message("删除成功") : RespBean.error().message("删除失败");
    }

}
