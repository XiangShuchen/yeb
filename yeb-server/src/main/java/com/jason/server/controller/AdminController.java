package com.jason.server.controller;


import com.jason.server.pojo.Admin;
import com.jason.server.pojo.DTO.BaseDTO.RespBean;
import com.jason.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @GetMapping("/test")
    @ResponseBody
    public RespBean hello() {
        int i = 0;
        return RespBean.success("success");
    }

    @GetMapping("/devTest")
    @ResponseBody
    public String devTest() {
        int i = 0;
        return "devTestSuccess!!!!";
    }


    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("info")
    public RespBean getAdmin(Principal principal) {
        if (null == principal) {
            return RespBean.error();
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        admin.setPassword(null);
        return RespBean.success(admin);
    }
}
