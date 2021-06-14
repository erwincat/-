package com.server;

import com.server.pojo.Admin;
import com.server.pojo.RespBean;
import com.server.service.IAdminService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminTest extends TmallApplicationTests{
    @Autowired
    private IAdminService adminService;
    /*
        测试获取所有操作员
    */

    @Test
    public void testGetAllAdmins(){
        List<Admin> allAdmins = adminService.getAllAdmins(null);
        Assert.assertEquals("查询失败",allAdmins,adminService.getAllAdmins(null));
    }
    /*
    * 测试更新操作员角色
    * */
    @Test
    public void testUpdateAdminRole(){
        RespBean respBean = new RespBean(200,"更新成功",null);
        Assert.assertEquals("更新失败",respBean,adminService.updateAdminRole(2,new Integer[]{1,2}));
    }

}
