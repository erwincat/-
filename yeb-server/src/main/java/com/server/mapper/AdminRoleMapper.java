package com.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanglishen
 * @since 2021-05-07
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    /**
     * 添加操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
