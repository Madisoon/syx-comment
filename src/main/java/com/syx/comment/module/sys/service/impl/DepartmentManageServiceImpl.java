package com.syx.comment.module.sys.service.impl;

import com.syx.comment.entity.SysDepartment;
import com.syx.comment.module.sys.service.DepartmentManageService;
import com.syx.comment.repository.SysDepartmentRepository;
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

    @Override
    public SysDepartment saveDepartmentInformation(SysDepartment sysDepartment) {
        sysDepartment.setDepCreateTime(new Date());
        SysDepartment sysDepartmentReturn = sysDepartmentRepository.save(sysDepartment);
        return sysDepartmentReturn;
    }

    @Override
    public List<SysDepartment> getDepartmentInformation(String depPacketNo) {
        return sysDepartmentRepository.findSysDepartmentByDepPacketNo(depPacketNo);
    }

    @Override
    public void deleteDepartment(Long id) {
        sysDepartmentRepository.delete(id);
    }
}