package com.jason.server;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RespBean {
    private int code;
    private boolean success;
    private String message;
    private Object obj;

    private RespBean(int code, boolean success) {
        this.code = code;
        this.success = success;
    }



    public static RespBean success() {
        return new RespBean(200, true);
    }


    public static RespBean error() {
        return new RespBean(500, false);
    }

    public RespBean code(int code) {
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
