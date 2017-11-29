package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.*;
import com.syx.comment.module.sys.service.PacketManageService;
import com.syx.comment.repository.SysAreaPacketRepository;
import com.syx.comment.repository.SysPacketRepository;
import com.syx.comment.repository.SysRoleUserRepository;
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

    @Autowired
    SysRoleUserRepository sysRoleUserRepository;

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
        sysUserRepository.save(sysUser);
        SysAreaPacket sysAreaPacket = sysAreaPacketRepository.findSysAreaByPacketId(sysPacketReturn.getId());
        if (sysAreaPacket == null) {
            sysAreaPacket = new SysAreaPacket();
            sysAreaPacket.setAreaId(Long.valueOf(areaId));
            sysAreaPacket.setPacketId(sysPacketReturn.getId());
            sysAreaPacketRepository.save(sysAreaPacket);
        }

        SysRoleUser sysRoleUser = sysRoleUserRepository.findSysRoleUserByUserId(sysUser.getUserName());
        if (sysRoleUser == null) {
            sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(Long.parseLong("2"));
            sysRoleUser.setUserId(sysUser.getUserName());
            sysRoleUserRepository.save(sysRoleUser);
        }
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
                "sys_user b, sys_area_packet c, sys_role_user d  WHERE a.packet_no = b.user_packet_no  " +
                "AND a.id=c.packet_id AND b.user_name = d.user_id AND d.role_id = '2'  AND  ( " + StringUtils.join(list, "") + " )";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sql));
        return jsonArray;
    }

    @Override
    public JSONObject deletePacketInformation(String packetId) {
        String[] packetIdS = packetId.split(",");
        int packetIdSLen = packetIdS.length;
        List<SysAreaPacket> listArea = new ArrayList<>();
        List<SysPacket> listPacket = new ArrayList<>();
        for (int i = 0; i < packetIdSLen; i++) {
            Long id = Long.parseLong(packetIdS[i]);
            SysAreaPacket sysAreaPacket = new SysAreaPacket();
            SysAreaPacket sysAreaPacketData = sysAreaPacketRepository.findSysAreaByPacketId(id);
            SysPacket sysPacket = new SysPacket();
            sysAreaPacket.setId(sysAreaPacketData.getId());
            sysPacket.setId(id);
            listArea.add(sysAreaPacket);
            listPacket.add(sysPacket);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            sysPacketRepository.delete(listPacket);
            sysAreaPacketRepository.delete(listArea);
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }
}
