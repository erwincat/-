package com.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fc
 * @since 2021-05-07
 */
public interface RoleMapper extends BaseMapper<Role> {
/*
    List<Role> getRoles(Integer adminId);
*/
    /**
     * 根据用户ID查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Integer adminId);
}
