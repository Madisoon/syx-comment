package com.syx.comment.repository;

import com.syx.comment.entity.SysDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 部门的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:41
 */
public interface SysDepartmentRepository extends JpaRepository<SysDepartment, Long> {
}
