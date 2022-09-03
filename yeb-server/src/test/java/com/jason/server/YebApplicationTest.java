package com.jason.server;

import com.jason.server.mapper.MenuMapper;
import com.jason.server.pojo.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author jason.Xiang
 * @description :
 * @since 2022/9/3-21
 */

@SpringBootTest
public class YebApplicationTest {

    @Autowired
    private MenuMapper  menuMapper;

    @Test
    public void contextTest(){
        List<Menu> menusByAdminId = menuMapper.getMenusByAdminId(1);
        menusByAdminId.forEach(System.out::println);
    }
}
