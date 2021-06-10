package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fc
 * @since 2021-05-07
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenusByAdminId();

    List<Menu> getMenusWithRole();

    //获取所有菜单
    List<Menu> getAllMenus();
}
