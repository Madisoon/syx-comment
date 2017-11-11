package com.syx.comment.repository;

import com.syx.comment.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 角色菜单的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:55
 */
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu, Long> {
}
