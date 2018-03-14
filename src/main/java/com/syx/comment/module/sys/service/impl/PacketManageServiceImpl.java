package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.*;
import com.syx.comment.module.sys.service.PacketManageService;
import com.syx.comment.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    SysPacketRepository sysPacketRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysAreaPacketRepository sysAreaPacketRepository;

    @Autowired
    SysRoleUserRepository sysRoleUserRepository;

    @Autowired
    BaseDao baseDao;

    @Autowired
    SysTaskConfigRepository sysTaskConfigRepository;

    @Override
    // 发生任何异常都进行回滚操作
    @Transactional(rollbackFor = {Exception.class})
    public SysPacket savePacketInformation(SysPacket sysPacket, SysUser sysUser, String areaId) {
        // 添加默认值
        sysPacket.setIsUsing(1);
        sysPacket.setGmtCreate(new Date());
        sysPacket.setGmtModified(new Date());
        SysPacket sysPacketReturn = sysPacketRepository.save(sysPacket);
        // 用户添加默认值
        sysUser.setPacketNo(Long.parseLong(sysPacket.getPacketNo()));
        sysUser.setUserName("管理员");
        sysUser.setGmtCreate(new Date());
        sysUser.setGmtModified(new Date());
        sysUserRepository.save(sysUser);
        SysAreaPacket sysAreaPacket = sysAreaPacketRepository.findSysAreaByPacketId(sysPacketReturn.getId());
        if (sysAreaPacket == null) {
            sysAreaPacket = new SysAreaPacket();
            sysAreaPacket.setAreaId(Long.valueOf(areaId));
            sysAreaPacket.setPacketId(sysPacketReturn.getId());
            sysAreaPacket.setGmtModified(new Date());
            sysAreaPacket.setGmtCreate(new Date());
            sysAreaPacketRepository.save(sysAreaPacket);
        }

        SysRoleUser sysRoleUser = sysRoleUserRepository.findSysRoleUserByUserAccount(sysUser.getUserAccount());
        if (sysRoleUser == null) {
            sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(Long.parseLong("2"));
            sysRoleUser.setUserAccount(sysUser.getUserAccount());
            sysRoleUser.setGmtModified(new Date());
            sysRoleUser.setGmtCreate(new Date());
            sysRoleUserRepository.save(sysRoleUser);
        }

        SysTaskConfig sysTaskConfig = new SysTaskConfig();
        sysTaskConfig.setGmtCreate(new Date());
        sysTaskConfig.setGmtModified(new Date());
        sysTaskConfig.setIsYuqing(1);
        sysTaskConfig.setPacketNo(Long.parseLong(sysPacketReturn.getPacketNo()));
        sysTaskConfig.setTaskColor("#00CCFF");
        sysTaskConfig.setTaskConfigName("舆情上报");
        sysTaskConfig.setTaskExplain("舆情上报为特殊类型，可结合本公司舆情云系统使用！");
        sysTaskConfig.setTaskMark(15);
        sysTaskConfigRepository.save(sysTaskConfig);
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
        String sql = "SELECT a.*,b.user_account,b.user_password,b.id AS user_id FROM sys_packet a ,   " +
                "sys_user b, sys_area_packet c, sys_role_user d  WHERE a.packet_no = b.packet_no  " +
                "AND a.id=c.packet_id AND b.user_account = d.user_account AND d.role_id = '2'  AND  ( " + StringUtils.join(list, "") + " )";
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
        SysPacket sysPacketData = sysPacketRepository.getOne(Long.parseLong(packetId));
        String packetNo = sysPacketData.getPacketNo();
        String deleteConfig = "DELETE FROM  sys_task_config WHERE packet_no = " + packetNo + " ";
        baseDao.execute(deleteConfig, null);
        String finishConfig = "DELETE FROM  sys_task_finish WHERE packet_no = " + packetNo + " ";
        baseDao.execute(finishConfig, null);
        String releaseConfig = " DELETE FROM  sys_task_release WHERE  packet_no = " + packetNo + " ";
        baseDao.execute(releaseConfig, null);
        String userConfig = " DELETE FROM  sys_user WHERE packet_no = " + packetNo + " ";
        baseDao.execute(userConfig, null);

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

    @Override
    public SysPacket getSysPacketByPacketNo(String PacketNo) {
        return sysPacketRepository.findSysPacketByPacketNo(PacketNo);
    }
}
