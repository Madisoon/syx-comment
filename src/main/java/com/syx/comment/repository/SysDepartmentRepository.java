package com.syx.comment.repository;

import com.syx.comment.entity.SysDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 部门的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:41
 */
public interface SysDepartmentRepository extends JpaRepository<SysDepartment, Long> {
    /**
     * 根据编码寻找部门信息
     *
     * @param depPacketNo
     * @return
     */
    List<SysDepartment> findSysDepartmentByDepPacketNo(String depPacketNo);

    /**
     * 根据部门的编号获取部门的信息
     *
     * @param depNo
     * @return
     */
    SysDepartment findSysDepartmentByDepNo(String depNo);
}
