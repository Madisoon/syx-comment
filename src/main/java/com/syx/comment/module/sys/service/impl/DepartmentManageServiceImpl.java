package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.DepartmentManageService;
import com.syx.comment.repository.SysDepartmentRepository;
import com.syx.comment.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 部门管理接口实现类
 *
 * @author Msater Zg
 * @create 2017-11-07 19:15
 */

@Service
public class DepartmentManageServiceImpl implements DepartmentManageService {
    @Autowired
    SysDepartmentRepository sysDepartmentRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public SysDepartment saveDepartmentInformation(SysDepartment sysDepartment, SysUser sysUser) {
        sysDepartment.setDepCreateTime(new Date());
        SysDepartment sysDepartmentReturn = sysDepartmentRepository.save(sysDepartment);
        sysUser.setUserNickName("admin");
        sysUser.setUserCreateTime(new Date());
        sysUser.setUserRole(3);
        sysUserRepository.save(sysUser);
        return sysDepartmentReturn;
    }

    @Override
    public JSONArray getDepartmentInformation(String depPacketNo) {
        String sql = "SELECT a.id ,a.dep_no AS depNo,a.dep_name AS depName,b.user_name AS userName," +
                "b.user_pwd AS userPwd,b.id AS userId  " +
                "FROM sys_department a, sys_user b " +
                "WHERE a.dep_packet_no = ? AND a.dep_no = b.user_dep AND b.user_role = '3'";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sql, new String[]{depPacketNo}));
        return jsonArray;
    }

    @Override
    public void deleteDepartment(Long id) {
        sysDepartmentRepository.delete(id);
    }
}