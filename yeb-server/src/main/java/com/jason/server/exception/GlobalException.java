package com.jason.server.exception;


import com.jason.server.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author jason.Xiang
 * @description : 全局异常处理
 * @since 2022/9/7-22
*/
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public RespBean mysqlException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error().message("该数据有关联数据，操作失败");
        }
        return RespBean.error().message("数据库异常");
    }
}
