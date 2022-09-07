package com.jason.server.pojo.DTO.BaseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@Builder
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
        return success(200, null);
    }

    public static RespBean success(String message) {
        return new RespBean(HttpStatus.OK.value(),true, message, null);
        return success(200, message);
    }

    public static RespBean success(Object obj) {
        return new RespBean(HttpStatus.OK.value(), true,"操作成功", obj);
        return success(200, "操作成功", obj);
    }

    public static RespBean success(long code,String message) {
        return success(code, message, null);
    }

    public static RespBean success(long code,String message,Object obj){
        return new RespBean(code,message,obj);
    }




    public static RespBean error() {
        return new RespBean(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,null, null);
        return error(500, null);
    }

    public static RespBean error(long code) {
        return new RespBean(code, false,null, null);
        return error(code, null);
    }

    public static RespBean error(String message){
        return error(500,message);
    }

    public static RespBean error(long code, String message) {
        return error(code, message, null);
    }

    public static RespBean error(Object obj) {
        return error(500, null, obj);
    }

    private static RespBean error(long code, String message, Object obj) {
        return new RespBean(code, message, obj);
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
