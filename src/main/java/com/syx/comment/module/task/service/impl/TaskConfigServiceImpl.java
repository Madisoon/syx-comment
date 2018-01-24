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
    SysTaskReleaseDepartmentRepository sysTaskReleaseDepartmentRepository;

    @Autowired
    SysReadTabRepository sysReadTabRepository;

    @Autowired
    SysFinishTabRepository sysFinishTabRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public SysTaskConfig saveTaskConfigInformation(SysTaskConfig sysTaskConfig) {
        sysTaskConfig.setTaskTime(new Date());
        return sysTaskConfigRepository.save(sysTaskConfig);
    }

    @Override
    public List<SysTaskConfig> getTaskConfigByPacketNo(String packetNo) {
        return sysTaskConfigRepository.findSysTaskConfigByTaskPacketNo(Long.valueOf(packetNo));
    }

    @Override
    public SysTaskRelease saveTaskReleaseInformation(SysTaskRelease sysTaskRelease, String taskDep) {
        sysTaskRelease.setTaskCreateTime(new Date());
        sysTaskRelease = sysTaskReleaseRepository.save(sysTaskRelease);
        Long taskReleaseId = sysTaskRelease.getId();
        String sqlDelete = "DELETE FROM sys_task_release_department WHERE task_release_id = ?";
        baseDao.execute(sqlDelete, new String[]{String.valueOf(taskReleaseId)});
        if (!"".equals(taskDep)) {
            String[] taskDepS = taskDep.split(",");
            int taskDepSLen = taskDepS.length;
            for (int i = 0; i < taskDepSLen; i++) {
                String depNo = taskDepS[i];
                SysTaskReleaseDepartment sysTaskReleaseDepartment = new SysTaskReleaseDepartment();
                sysTaskReleaseDepartment.setDepNo(Long.parseLong(depNo));
                sysTaskReleaseDepartment.setTaskReleaseId(taskReleaseId);
                sysTaskReleaseDepartmentRepository.save(sysTaskReleaseDepartment);
            }
        }
        return sysTaskRelease;
    }

    @Override
    public JSONObject getTaskReleaseInformation(String sysPacketNo, String pageNumber, String pageSize) {
        String selectSqlTotal = "SELECT * FROM sys_task_release a WHERE task_packet_no = '" + sysPacketNo + "' ";
        String selectSql = "SELECT a.*,COUNT(b.id) AS dep_number,GROUP_CONCAT(b.dep_no) as dep_nos FROM " +
                "(SELECT a.*,b.task_config_name FROM sys_task_release a ,sys_task_config b  " +
                "WHERE  a.task_config_id = b.id AND a.task_packet_no = '" + sysPacketNo + "'  ORDER BY a.task_create_time DESC  " + SqlEasy.limitPage(pageSize, pageNumber) + " ) a " +
                "LEFT JOIN sys_task_release_department b ON a.id = b.task_release_id GROUP BY a.id ";
        List<Map<String, String>> listTotal = baseDao.rawQuery(selectSqlTotal);
        List<Map<String, String>> list = baseDao.rawQuery(selectSql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", listTotal.size());
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @Override
    public List<SysTaskReleaseDepartment> saveTaskDepartmentInformation(String taskId, String taskDep) {
        String[] taskDepS = taskDep.split(",");
        int taskDepSLen = taskDepS.length;
        List<SysTaskReleaseDepartment> list = new ArrayList<>();
        for (int i = 0; i < taskDepSLen; i++) {
            SysTaskReleaseDepartment sysTaskReleaseDepartment = new SysTaskReleaseDepartment();
            sysTaskReleaseDepartment.setTaskReleaseId(Long.parseLong(taskId));
            sysTaskReleaseDepartment.setDepNo(Long.parseLong(taskDepS[i]));
            list.add(sysTaskReleaseDepartment);
        }
        list = sysTaskReleaseDepartmentRepository.save(list);
        return list;
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
            SysTaskRelease sysTaskRelease = new SysTaskRelease();
            sysTaskRelease.setId(Long.parseLong(taskIdS[i]));
            list.add(sysTaskRelease);
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
    public JSONObject getAllNoteInformation(String sysPacketNo, String depNo, String userName, String pageSize, String pageNumber) {
        String sqlTotal = "SELECT a.*,b.id AS finish_id,c.id AS read_id FROM (SELECT a.*,c.task_config_name FROM sys_task_release a,    " +
                "sys_task_release_department b,sys_task_config c   " +
                "WHERE a.id = b.task_release_id AND a.task_config_id = c.id    " +
                "AND a.task_packet_no = ?   " +
                "AND b.dep_no = ? ORDER BY a.task_create_time DESC ) a  " +
                "LEFT JOIN (SELECT * FROM sys_finish_tab WHERE user_name = ? ) b ON a.id = b.task_id " +
                "LEFT JOIN (SELECT * FROM sys_read_tab WHERE user_name = ? ) c ON a.id = c.task_id   ";
        String sqlSelect = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber) + "";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlSelect, new String[]{sysPacketNo, depNo, userName, userName}));
        JSONArray jsonArrayTotal = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlTotal, new String[]{sysPacketNo, depNo, userName, userName}));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", jsonArray);
        System.out.println(jsonArray);
        jsonObject.put("total", jsonArrayTotal.size());
        return jsonObject;
    }

    @Override
    public JSONObject tabReadOrFinish(String type, String taskReleaseId, String userName) {
        String readTab = "0";
        if (readTab.equals(type)) {
            SysReadTab sysReadTab = new SysReadTab();
            sysReadTab.setTaskId(Long.parseLong(taskReleaseId));
            sysReadTab.setUserName(userName);
            sysReadTabRepository.save(sysReadTab);
        } else {
            SysFinishTab sysFinishTab = new SysFinishTab();
            sysFinishTab.setTaskId(Long.parseLong(taskReleaseId));
            sysFinishTab.setUserName(userName);
            sysFinishTabRepository.save(sysFinishTab);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", 1);
        return jsonObject;
    }

    @Override
    public JSONObject removeReadOrFinish(String type, String id) {
        String readTab = "0";
        if (readTab.equals(type)) {
            sysReadTabRepository.delete(Long.parseLong(id));
        } else {
            sysFinishTabRepository.delete(Long.parseLong(id));
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", 1);
        return jsonObject;
    }

    @Override
    public JSONObject getTaskReleaseByNumber(String taskNumber, String depNo) {
        String selectSql = "SELECT * FROM sys_task_release a ,sys_task_release_department b " +
                "WHERE a.id = b.task_release_id AND a.task_number = ? " +
                "AND b.dep_no = ? AND a.task_finish_time >= ? ";
        Map map = baseDao.rawQueryForMap(selectSql, new String[]{taskNumber, depNo, DateOrTimeUtil.getNowTimeByDifferentFormat("yyyy-MM-dd HH-mm")});
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
}
