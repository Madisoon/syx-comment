package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.entity.SysRoleUser;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.DepartmentManageService;
import com.syx.comment.repository.SysDepartmentRepository;
import com.syx.comment.repository.SysRoleUserRepository;
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
    SysRoleUserRepository sysRoleUserRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public SysDepartment saveDepartmentInformation(SysDepartment sysDepartment, SysUser sysUser) {
        sysDepartment.setDepCreateTime(new Date());
        SysDepartment sysDepartmentReturn = sysDepartmentRepository.save(sysDepartment);
        sysUser.setUserNickName("admin");
        sysUser.setUserCreateTime(new Date());
        sysUserRepository.save(sysUser);
        SysRoleUser sysRoleUser = sysRoleUserRepository.findSysRoleUserByUserId(sysUser.getUserName());
        if (sysRoleUser == null) {
            sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(Long.parseLong("3"));
            sysRoleUser.setUserId(sysUser.getUserName());
            sysRoleUserRepository.save(sysRoleUser);
        }
        return sysDepartmentReturn;
    }

    @Override
    public JSONArray getDepartmentInformation(String depPacketNo) {
        String sql = " SELECT a.id ,a.dep_no AS depNo,a.dep_name AS depName,b.user_name AS userName,  " +
                "b.user_pwd AS userPwd,b.id AS userId  " +
                "FROM sys_department a LEFT JOIN sys_user b ON  a.dep_no = b.user_dep  " +
                "LEFT JOIN sys_role_user c ON b.user_name=c.user_id " +
                "WHERE a.dep_packet_no = ? AND a.dep_no = b.user_dep  AND c.role_id = '3'";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sql, new String[]{depPacketNo}));
        return jsonArray;
    }

    @Override
    public void deleteDepartment(Long id) {
        SysDepartment sysDepartment = sysDepartmentRepository.getOne(id);
        String depNo = sysDepartment.getDepNo();
        List<SysUser> userList = sysUserRepository.findSysUserByUserDep(Integer.parseInt(depNo));
        int userListLen = userList.size();
        for (int i = 0; i < userListLen; i++) {
            SysUser sysUser = userList.get(i);
            String userName = sysUser.getUserName();
            String roleDelete = "DELETE FROM sys_role_user WHERE user_id = ? ";
            baseDao.execute(roleDelete, new String[]{userName});
            String finishDelete = "DELETE FROM sys_task_finish WHERE task_creater = ? ";
            baseDao.execute(roleDelete, new String[]{finishDelete});
        }
        String dep = "DELETE FROM sys_user WHERE user_dep = ? ";
        baseDao.execute(dep, new String[]{depNo});
        String releaseDelete = "DELETE FROM sys_task_release_department WHERE dep_no = ? ";
        baseDao.execute(releaseDelete, new String[]{depNo});
        sysDepartmentRepository.delete(id);
    }
}