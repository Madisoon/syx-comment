package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysUser;

/**
 * 描述:
 * 数据包管理的api
 *
 * @author Msater Zg
 * @create 2017-11-11 10:08
 */
public interface PacketManageService {
    /**
     * 更细并保存数据包的信息
     *
     * @param sysPacket
     * @param sysUser
     * @return SysPacket
     */
    SysPacket savePacketInformation(SysPacket sysPacket, SysUser sysUser);

    JSONArray getPacketInformation(String areaId);

}
