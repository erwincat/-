package com.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fc
 * @since 2021-05-07
 */
public interface IRoleService extends IService<Role> {
    List<Role> getRoles(Integer adminId);
}
