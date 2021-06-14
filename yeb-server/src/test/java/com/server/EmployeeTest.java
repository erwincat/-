package com.server;

import com.server.pojo.Employee;
import com.server.pojo.RespBean;
import com.server.service.IEmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class EmployeeTest extends TmallApplicationTests{
    @Autowired
    private IEmployeeService employeeService;
    /*
    * 测试添加员工
    * */
    @Test
    public void testInsertEmployee(){
        Employee employee = new Employee();
        employee.setAddress("123");
        employee.setName("fc");
        LocalDate date = LocalDate.of(Integer.parseInt("2018"), Integer.parseInt("7"), Integer.parseInt("28"));
        employee.setBeginContract(date);
        employee.setEndContract(date);
        RespBean respBean = new RespBean(200, "添加成功", null);
        Assert.assertEquals("添加失败",respBean,employeeService.insertEmployee(employee));
    }
    @Test
    /*
    * 测试通过Id 查询员工
    * */
    public void testGetEmployee(){
        List<Employee> employee = employeeService.getEmployee(13);
        Assert.assertEquals("查询失败",employee,employeeService.getEmployee(13));

    }
}
