package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Admin;
import com.server.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanglishen
 * @since 2020-11-14
 */
public interface IAdminService extends IService<Admin> {


    RespBean login(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUserName(String username);
}
/*
    List<Role> getRolesByAdminId(Integer adminId);}
*/
