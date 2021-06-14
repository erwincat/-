package com.server;

import com.server.pojo.Position;
import com.server.pojo.RespBean;
import com.server.service.IPositionService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PositionTest extends TmallApplicationTests{

    @Autowired
    private IPositionService positionService;
    @Test
    /*
    * 测试获取职位信息
    * */
    public void testGetPosition(){
        List<Position> list =  positionService.list();
        Assert.assertEquals("查询失败",list,positionService.list());
    }
    @Test
    /*
    * 测试添加职位信息
    * */
    public void testAddPosition(){
        Position position = new Position();
        position.setName("开发总监");
        position.setEnabled(true);
        RespBean respBean = new RespBean(200,"添加成功",null);
        Assert.assertEquals("添加失败",respBean,positionService.save(position));
    }
}
