package com.syx.comment.module.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.*;
import com.syx.comment.module.task.service.TaskConfigService;
import com.syx.comment.repository.*;
import com.syx.comment.utils.DateOrTimeUtil;
import com.syx.comment.utils.SqlEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 任务配置的接口实现
 *
 * @author Msater Zg
 * @create 2017-11-13 19:29
 */
@Service
public class TaskConfigServiceImpl implements TaskConfigService {
    @Autowired
    SysTaskConfigRepository sysTaskConfigRepository;

    @Autowired
    SysTaskReleaseRepository sysTaskReleaseRepository;

    @Autowired
    SysTaskReleaseUserRepository sysTaskReleaseUserRepository;

    @Autowired
    SysReadTabRepository sysReadTabRepository;

    @Autowired
    SysFinishTabRepository sysFinishTabRepository;

    @Autowired
    SysStarTabRepository sysStarTabRepository;

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public SysTaskConfig saveTaskConfigInformation(SysTaskConfig sysTaskConfig) {
        sysTaskConfig.setGmtCreate(new Date());
        sysTaskConfig.setGmtModified(new Date());
        return sysTaskConfigRepository.save(sysTaskConfig);
    }

    @Override
    public List<SysTaskConfig> getTaskConfigByPacketNo(String packetNo) {
        return sysTaskConfigRepository.findSysTaskConfigByPacketNo(Long.valueOf(packetNo));
    }

    @Override
    public SysTaskRelease saveTaskReleaseInformation(SysTaskRelease sysTaskRelease, String userId) {
        sysTaskRelease.setGmtCreate(new Date());
        sysTaskRelease.setGmtModified(new Date());
        sysTaskRelease = sysTaskReleaseRepository.save(sysTaskRelease);
        Long taskReleaseId = sysTaskRelease.getId();
        String sqlDelete = "DELETE FROM sys_task_release_user WHERE task_release_id = ?";
        baseDao.execute(sqlDelete, new String[]{String.valueOf(taskReleaseId)});
        if (!"".equals(userId)) {
            String[] userIdS = userId.split(",");
            int userIdSLen = userIdS.length;
            for (int i = 0; i < userIdSLen; i++) {
                SysUser sysUser = sysUserRepository.findOne(Long.parseLong(userIdS[i]));
                SysTaskReleaseUser sysTaskReleaseUser = new SysTaskReleaseUser();
                sysTaskReleaseUser.setTaskReleaseId(taskReleaseId);
                sysTaskReleaseUser.setReceiverAccount(sysUser.getUserAccount());
                sysTaskReleaseUser.setGmtCreate(new Date());
                sysTaskReleaseUser.setGmtModified(new Date());
                sysTaskReleaseUserRepository.save(sysTaskReleaseUser);
            }
        }
        return sysTaskRelease;
    }

    @Override
    public JSONArray getTaskReleaseInformation(String userAccount) {
        String sql = "SELECT a.*,GROUP_CONCAT(b.receiver_account) AS receiver_accounts,GROUP_CONCAT(b.user_name) AS user_names " +
                "FROM (SELECT a.*,b.task_config_name,b.task_color FROM sys_task_release a ,sys_task_config b WHERE   " +
                "a.user_account = ? AND a.task_config_id = b.id ) a    " +
                "LEFT JOIN (SELECT a.*,b.user_name FROM sys_task_release_user a ,sys_user b  " +
                "WHERE a.receiver_account = b.user_account) b  " +
                "ON a.id = b.task_release_id GROUP BY a.id ";
        List list = baseDao.rawQuery(sql, new String[]{userAccount});
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        return jsonArray;
    }

    @Override
    public JSONObject deleteTaskConfigInformation(String taskConfigId) {
        JSONObject jsonObject = new JSONObject();
        try {
            sysTaskConfigRepository.delete(Long.parseLong(taskConfigId));
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public JSONObject deleteTaskInformation(String taskId) {
        String[] taskIdS = taskId.split(",");
        int taskIdSLen = taskIdS.length;
        List<SysTaskRelease> list = new ArrayList<>();
        for (int i = 0; i < taskIdSLen; i++) {
            Long taskReleaseId = Long.parseLong(taskIdS[i]);
            SysTaskRelease sysTaskRelease = new SysTaskRelease();
            sysTaskRelease.setId(taskReleaseId);
            list.add(sysTaskRelease);
            List<SysTaskReleaseUser> userList = sysTaskReleaseUserRepository.findSysTaskReleaseUserByTaskReleaseId(taskReleaseId);
            sysTaskReleaseUserRepository.delete(userList);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            sysTaskReleaseRepository.delete(list);
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getAllNoteInformation(String sysPacketNo, String userAccount, String pageSize, String pageNumber) {
        String sqlTotal = " SELECT a.*,b.id AS finish_id,c.id AS read_id,d.id AS star_id FROM (SELECT a.*,c.task_config_name FROM sys_task_release a, " +
                " sys_task_release_user b,sys_task_config c  " +
                " WHERE a.id = b.task_release_id AND a.task_config_id = c.id AND a.is_posted = 1  " +
                " AND a.packet_no = ? AND b.receiver_account = ? ) a  " +
                " LEFT JOIN (SELECT * FROM sys_finish_tab WHERE user_account = ? ) b ON a.id = b.task_id  " +
                " LEFT JOIN (SELECT * FROM sys_read_tab WHERE user_account = ? ) c ON a.id = c.task_id  " +
                "LEFT JOIN (SELECT * FROM sys_star_tab WHERE user_account = ? ) d ON a.id = d.task_id " +
                "ORDER BY a.gmt_create DESC ";
        String sqlSelect = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber) + "";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlSelect, new String[]{sysPacketNo, userAccount, userAccount, userAccount, userAccount}));
        JSONArray jsonArrayTotal = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlTotal, new String[]{sysPacketNo, userAccount, userAccount, userAccount, userAccount}));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        jsonObject.put("total", jsonArrayTotal.size());
        return jsonObject;
    }

    @Override
    public JSONObject tabReadOrFinish(String type, String taskReleaseId, String userName) {
        String starTab = "0";
        String finishTab = "1";
        if (starTab.equals(type)) {
            SysStarTab sysStarTab = new SysStarTab();
            sysStarTab.setTaskId(Long.parseLong(taskReleaseId));
            sysStarTab.setUserAccount(userName);
            sysStarTab.setGmtCreate(new Date());
            sysStarTab.setGmtModified(new Date());
            sysStarTabRepository.save(sysStarTab);
        } else if (finishTab.equals(type)) {
            SysFinishTab sysFinishTab = new SysFinishTab();
            sysFinishTab.setTaskId(Long.parseLong(taskReleaseId));
            sysFinishTab.setUserAccount(userName);
            sysFinishTab.setGmtCreate(new Date());
            sysFinishTab.setGmtModified(new Date());
            sysFinishTabRepository.save(sysFinishTab);
        } else {
            SysReadTab sysReadTab = new SysReadTab();
            sysReadTab.setTaskId(Long.parseLong(taskReleaseId));
            sysReadTab.setUserAccount(userName);
            sysReadTab.setGmtCreate(new Date());
            sysReadTab.setGmtModified(new Date());
            sysReadTabRepository.save(sysReadTab);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", 1);
        return jsonObject;
    }

    @Override
    public JSONObject removeReadOrFinish(String type, String id) {
        String readTab = "0";
        if (readTab.equals(type)) {
            sysStarTabRepository.delete(Long.parseLong(id));
        } else {
            sysFinishTabRepository.delete(Long.parseLong(id));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", 1);
        return jsonObject;
    }

    @Override
    public JSONObject getTaskReleaseByNumber(String taskNumber, String userAccount) {
        String selectSql = " SELECT * FROM sys_task_release a ,sys_task_release_user b  " +
                " WHERE a.id = b.task_release_id AND a.task_number = ?  " +
                " AND b.receiver_account = ? AND a.task_end_time >= ? ";
        Map map = baseDao.rawQueryForMap(selectSql, new String[]{taskNumber, userAccount, DateOrTimeUtil.getNowTimeByDifferentFormat("yyyy-MM-dd HH-mm")});
        JSONObject jsonObject = new JSONObject();
        if (map == null) {
            jsonObject.put("result", 0);
        } else {
            jsonObject.put("result", 1);
            JSONObject jsonObjectMap = (JSONObject) JSON.toJSON(map);
            jsonObject.put("data", jsonObjectMap);
        }
        return jsonObject;
    }

    @Override
    public SysTaskRelease updateTaskReleaseStatus(String id) {
        JSONObject jsonObject = new JSONObject();
        SysTaskRelease sysTaskRelease = sysTaskReleaseRepository.getOne(Long.parseLong(id));
        sysTaskRelease.setIsPosted(1);
        sysTaskRelease = sysTaskReleaseRepository.save(sysTaskRelease);
        return sysTaskRelease;
    }
}
