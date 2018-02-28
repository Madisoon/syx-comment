package com.syx.comment.module.sys.service.impl;

import com.syx.comment.entity.SysArea;
import com.syx.comment.module.sys.service.AreaManageService;
import com.syx.comment.repository.SysAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 地区管理接口实现
 *
 * @author Msater Zg
 * @create 2017-11-07 19:19
 */

@Service
public class AreaManageServiceImpl implements AreaManageService {
    @Autowired
    SysAreaRepository sysAreaRepository;

    @Override
    public SysArea saveAreaInformation(SysArea sysArea) {
        return sysAreaRepository.save(sysArea);
    }

    @Override
    public List<SysArea> getAllAreaInformation() {
        return sysAreaRepository.findAll();
    }

    @Override
    public List<SysArea> deleteAreaInformation(String areaId) {
        String[] areaIdS = areaId.split(",");
        int areaIdSLen = areaIdS.length;
        List<SysArea> list = new ArrayList<>();
        for (int i = 0; i < areaIdSLen; i++) {
            SysArea sysArea = new SysArea();
            sysArea.setId(Long.parseLong(areaIdS[i]));
            list.add(sysArea);
        }
        sysAreaRepository.delete(list);
        return list;
    }
}
