package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.entity.SysUser;

/**
 * 描述:
 * 系统用户管理——API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:14
 */
public interface DepartmentManageService {
    /**
     * 添加部门，同时添加本部门部长的账号
     *
     * @param sysDepartment
     * @param sysUser
     * @return
     */
    SysDepartment saveDepartmentInformation(SysDepartment sysDepartment, SysUser sysUser);

    /**
     * 根据数据包编码的得到所有的部门信息
     *
     * @param depPacketNo
     * @return
     */
    JSONArray getDepartmentInformation(String depPacketNo);

    /**
     * 删除部门信息
     *
     * @param id
     */
    void deleteDepartment(Long id);

    /**
     * 判断该部门是否已有编号
     *
     * @param depNo
     * @return
     */
    SysDepartment getDepartmentByDepNo(String depNo);

    /**
     * 根据部门编号获取部门的人员信息
     *
     * @param depNo
     * @param packetNo
     * @return
     */
    JSONArray listDepartmentUserByDepNo(String depNo, String packetNo);
}
