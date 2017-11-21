package com.syx.comment.module.sys.service;

import com.syx.comment.entity.SysArea;

import java.util.List;

/**
 * 描述:
 * 地区管理的API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:17
 */
public interface AreaManageService {
    /**
     *保存地区信息
     * @param sysArea
     * @return
     */
    SysArea saveAreaInformation(SysArea sysArea);

    /**
     *得到所有的地区信息
     * @return
     */
    List<SysArea> getAllAreaInformation();

    /**
     *删除地区的信息
     * @param areaId
     * @return
     */
    List<SysArea> deleteAreaInformation(String areaId);
}
