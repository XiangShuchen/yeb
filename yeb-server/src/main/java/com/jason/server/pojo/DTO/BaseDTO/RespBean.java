package com.jason.server.pojo.DTO.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    private RespBean(long code, String message, Object obj) {
        this.code = code;
        this.message = message;
        this.obj = obj;
    }

    private RespBean() {
    }

    public static RespBean success() {
        return new RespBean(200, null, null);
    }

    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean success(Object obj) {
        return new RespBean(200, "操作成功", obj);
    }


    public static RespBean error() {
        return new RespBean(500, null, null);
    }

    public static RespBean error(long code) {
        return new RespBean(code, null, null);
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
