package com.syx.comment.module.sys.service;

import com.syx.comment.entity.SysDepartment;

import java.util.List;

/**
 * 描述:
 * 系统用户管理——API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:14
 */
public interface DepartmentManageService {
    /**
     * 添加部门
     *
     * @param sysDepartment
     * @return
     */
    SysDepartment saveDepartmentInformation(SysDepartment sysDepartment);

    /**
     * 根据数据包编码的得到所有的部门信息
     *
     * @param depPacketNo
     * @return
     */
    List<SysDepartment> getDepartmentInformation(String depPacketNo);

    /**
     * 删除部门信息
     * @param id
     */
    void deleteDepartment(Long id);
}
