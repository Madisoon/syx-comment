package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
     * @param areaId
     * @return SysPacket
     */
    SysPacket savePacketInformation(SysPacket sysPacket, SysUser sysUser, String areaId);

    /**
     * 根据地区信息得到全国所有的系统
     *
     * @param areaId
     * @return
     */
    JSONArray getPacketInformation(String areaId);

    /**
     * 删除系统
     *
     * @param packetId
     * @return JSONObject
     */
    JSONObject deletePacketInformation(String packetId);

    /**
     * 根据编号获取部门
     *
     * @param PacketNo
     * @return
     */
    SysPacket getSysPacketByPacketNo(String PacketNo);

}
