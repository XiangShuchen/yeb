package com.jason.server;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode
public class RespBean {
    private long code;
    private boolean success;
    private String message;
    private Object obj;

    private RespBean(long code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }
    private RespBean(long code, boolean success,String message, Object obj) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.obj = obj;
    }

    private RespBean() {
    }

    public static RespBean success() {
        return new RespBean(HttpStatus.OK.value(),true, null, null);
    }

    public static RespBean success(String message) {
        return new RespBean(HttpStatus.OK.value(),true, message, null);
    }

    public static RespBean success(Object obj) {
        return new RespBean(HttpStatus.OK.value(), true,"操作成功", obj);
    }


    public static RespBean error() {
        return new RespBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,null, null);
    }

    public static RespBean error(long code) {
        return new RespBean(code, false,null, null);
    }

    public RespBean code(long code) {
        this.code = code;
        return this;
    }

    public RespBean message(String message) {
        this.message = message;
        return this;
    }

    public RespBean obj(Object obj) {
        this.obj = obj;
        return this;
    }


}
