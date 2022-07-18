package com.jason.server.pojo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    public static RespBean success() {
        return new RespBean(200, null, null);
    }

    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }


    public static RespBean error() {
        return new RespBean(500, null, null);
    }

    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    public static RespBean error(String message, Object obj) {
        return new RespBean(500, message, obj);
    }

}
