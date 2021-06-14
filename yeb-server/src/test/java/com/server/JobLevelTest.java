package com.server;

import com.server.pojo.Joblevel;
import com.server.service.IJoblevelService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JobLevelTest extends TmallApplicationTests{
    @Autowired
    private IJoblevelService joblevelService;
    @Test
    public void testGetAllJoblevel(){
        List<Joblevel> list = joblevelService.list();
        Assert.assertEquals("查询失败",list,joblevelService.list());

    }
}
