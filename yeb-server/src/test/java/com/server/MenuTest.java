package com.server;

import com.server.pojo.Menu;
import com.server.service.IMenuService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MenuTest extends TmallApplicationTests{
    @Autowired
    private IMenuService menuService;
    @Test
    /*
    * 测试通过用户Id获取 列表
    * */
    public void testGetMenusByAdminId(){
        List<Menu> allMenus = menuService.getAllMenus();
        Assert.assertEquals("查询失败",allMenus,menuService.getAllMenus());
    }
}
