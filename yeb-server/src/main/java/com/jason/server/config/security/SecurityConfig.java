package com.jason.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration auth;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired
    private IUserDetailsService userDetailsService;


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //使用jwt 不使用csrf
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //添加jwt登录授权过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                //添加自定义未授权结果返回
                .userDetailsService(userDetailsService).exceptionHandling().authenticationEntryPoint(restAuthorizationEntryPoint).accessDeniedHandler(restfulAccessDeniedHandler).and()
                //清除缓存
                .headers().cacheControl().disable().and()
                //配置路由
                .authorizeRequests(authorize -> authorize
                        //允许访问
                        .antMatchers("/login",
                                "/logout",
                                "/css/**",
                                "/js/**",
                                "/index.html",
                                "favicon.ico",
                                "/doc.html",
                                "/webjars/**",
                                "/swagger-resources/**",
                                "/v2/api-docs/**").permitAll()
                        //其他路由需要验证
                        .anyRequest().authenticated())
                .build();
    }
}



























