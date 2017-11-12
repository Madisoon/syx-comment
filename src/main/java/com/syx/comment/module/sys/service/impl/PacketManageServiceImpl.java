package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.PacketManageService;
import com.syx.comment.repository.SysPacketRepository;
import com.syx.comment.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述:
 * 数据包管理的接口实现
 *
 * @author Msater Zg
 * @create 2017-11-11 10:09
 */

@Service
public class PacketManageServiceImpl implements PacketManageService {
    @Autowired
    BaseDao baseDao;

    @Autowired
    SysPacketRepository sysPacketRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Override
    public SysPacket savePacketInformation(SysPacket sysPacket, SysUser sysUser) {
        // 添加默认值
        sysPacket.setPacketStatus(1);
        sysPacket.setPacketStartTime(new Date());
        SysPacket sysPacketReturn = sysPacketRepository.save(sysPacket);
        // 用户添加默认值
        sysUser.setUserPacketNo(sysPacket.getPacketNo());
        sysUser.setUserNickName("admin");
        sysUser.setUserCreateTime(new Date());
        sysUser.setUserRole(1);
        sysUserRepository.save(sysUser);
        return sysPacketReturn;
    }

    @Override
    public JSONArray getPacketInformation(String areaId) {
        String sql = "SELECT a.*,b.user_name,b.user_pwd FROM sys_packet a , " +
                "sys_user b  WHERE a.packet_no = b.user_packet_no";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sql));
        return jsonArray;
    }
}
