package com.server;

import com.server.pojo.Role;
import com.server.service.IRoleService;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleTest extends TmallApplicationTests{
    @Autowired
    private IRoleService iRoleService;

    public void testgetRole(){
        List<Role> roles = iRoleService.getRoles(1);
        Assert.assertEquals("查询失败",roles,iRoleService.getRoles(1));

    }

}
