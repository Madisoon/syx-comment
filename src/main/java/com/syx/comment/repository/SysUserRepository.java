package com.syx.comment.repository;

import com.syx.comment.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 系统用户的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 11:00
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    /**
     * 根据登陆名称获取个人信息
     *
     * @param userAccount
     * @return
     */
    SysUser findSysUserByUserAccount(String userAccount);

    /**
     * 根据部门获取部门用户
     *
     * @param userDep
     * @return
     */
    List<SysUser> findSysUserByUserDep(int userDep);
}
