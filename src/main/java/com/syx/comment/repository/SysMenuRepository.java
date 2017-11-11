package com.syx.comment.repository;

import com.syx.comment.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 菜单的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:51
 */
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {
}
