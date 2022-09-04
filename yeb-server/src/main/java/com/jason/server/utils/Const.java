package com.jason.server.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Const {

    public static final List<String> URL_LIST = Stream.of("/login",
            "/captcha",
            "/logout",
            "/css/",
            "/js/",
            "/index.html",
            "/favicon.ico",
            "/doc.html",
            "/webjars/",
            "/swagger-resources/",
            "/v2/api-docs/").collect(Collectors.toList());

    public static final String ROLE_MENU = "menuWithRole";
}
