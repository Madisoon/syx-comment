package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysAreaPacket;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.PacketManageService;
import com.syx.comment.repository.SysAreaPacketRepository;
import com.syx.comment.repository.SysPacketRepository;
import com.syx.comment.repository.SysUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    SysAreaPacketRepository sysAreaPacketRepository;

    @Override
    public SysPacket savePacketInformation(SysPacket sysPacket, SysUser sysUser, String areaId) {
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
        SysAreaPacket sysAreaPacket = new SysAreaPacket();
        sysAreaPacket.setAreaId(Long.valueOf(areaId));
        sysAreaPacket.setPacketId(sysPacketReturn.getId());
        sysAreaPacketRepository.save(sysAreaPacket);
        return sysPacketReturn;
    }

    @Override
    public JSONArray getPacketInformation(String areaId) {
        String[] areaIdS = areaId.split(",");
        int areaIdSLen = areaIdS.length;
        List<String> list = new ArrayList<>(16);
        for (int i = 0; i < areaIdSLen; i++) {
            if (i == 0) {
                list.add(" c.area_id = " + areaIdS[i] + " ");
            } else {
                list.add(" OR c.area_id = " + areaIdS[i] + " ");
            }
        }
        String sql = "SELECT a.*,b.user_name,b.user_pwd,b.id AS user_id FROM sys_packet a ,   " +
                "sys_user b, sys_area_packet c  WHERE a.packet_no = b.user_packet_no  " +
                "AND b.user_role = '1' AND a.id=c.packet_id  AND  ( " + StringUtils.join(list, "") + " )";
        System.out.println(sql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sql));
        return jsonArray;
    }
}
