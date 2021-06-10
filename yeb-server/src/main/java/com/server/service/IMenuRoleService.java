package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.MenuRole;
import com.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fc
 * @since 2021-05-07
 */
public interface IMenuRoleService extends IService<MenuRole> {
    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
