package com.syx.comment.repository;

import com.syx.comment.entity.SysTaskReleaseDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 发布任务适用部门的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:59
 */
public interface SysTaskReleaseDepartmentRepository extends JpaRepository<SysTaskReleaseDepartment, Long> {
    /**
     * 根据任务编号和部门查找信息
     * @param taskReleaseId
     * @param depNo
     * @return
     */
    SysTaskReleaseDepartment findSysTaskReleaseDepartmentByTaskReleaseIdAndDepNo(Long taskReleaseId, String depNo);
}
