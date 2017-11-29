package com.syx.comment.repository;

import com.syx.comment.entity.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 角色用户的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:56
 */
public interface SysRoleUserRepository extends JpaRepository<SysRoleUser, Long> {
    /**
     * 根据用户账号获取用户名
     *
     * @param userName
     * @return
     */
    SysRoleUser findSysRoleUserByUserId(String userName);
}
