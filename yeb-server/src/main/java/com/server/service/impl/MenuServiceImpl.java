package com.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.server.mapper.MenuMapper;
import com.server.pojo.Admin;
import com.server.pojo.Menu;
import com.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fc
 * @since 2021-05-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 通过用户id获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Integer adminId = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
/*        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //查看缓存中有没有数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        if (CollectionUtils.isEmpty(menus)) {
            //如果没有数据,数据库查询,并设置到缓存*/
        List<Menu> menus  = menuMapper.getMenusByAdminId(adminId);
/*            valueOperations.set("menu_" + adminId, menus);
        }*/
        return menus;
    }
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


}
