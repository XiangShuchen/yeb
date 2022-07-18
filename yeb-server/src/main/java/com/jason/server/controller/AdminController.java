package com.jason.server.controller;


import com.jason.server.pojo.DTO.RespBean;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jason.Xiang
 * @since 2022-07-18
 */
@RestController
@RequestMapping("/server/admin")
public class AdminController {

    @GetMapping("/test")
    public RespBean hello() {
        return RespBean.success("success");
    }
}
