package com.syx.comment.repository;

import com.syx.comment.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 角色的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:54
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
