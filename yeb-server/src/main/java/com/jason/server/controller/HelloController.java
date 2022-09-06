package com.jason.server.controller;

import com.jason.server.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason.Xiang
 * @description :
 * @since 2022/9/4-17
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public RespBean hello() {
        return RespBean.success("hello");
    }


    @GetMapping("/employee/basic/hello")
    public RespBean hello1(){
        return RespBean.success("/employee/basic/hello");
    }

    @GetMapping("/employee/advanced/hello")
    public RespBean hello2(){
        return RespBean.success("/employee/advanced/hello");
    }

}
