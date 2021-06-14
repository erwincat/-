package com.server;

import com.server.pojo.Department;
import com.server.pojo.RespBean;
import com.server.service.IDepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentTest extends TmallApplicationTests {
    @Autowired
    private IDepartmentService departmentService;
    /*
    * 测试查询 获取所有部门
    * */
    @Test
    public void testGetAllDepartments() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        Assert.assertEquals("查询失败",allDepartments,departmentService.getAllDepartments());
    }
    /*
    * 测试获取所有部门
    * */
    @Test
    public void testAddDepartment(){
        Department department = new Department();
        department.setName("产品平台部")
        .setParentId(3).setDepPath(".1.2.3.13").setEnabled(true).setIsParent(false);
        RespBean respBean = new RespBean(200,"添加成功",null);
        Assert.assertEquals("添加失败",respBean,departmentService.addDep(department));

    }

}
