package com.syx.comment.module.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.entity.SysTaskRelease;
import com.syx.comment.entity.SysTaskReleaseDepartment;
import com.syx.comment.module.task.service.TaskConfigService;
import com.syx.comment.repository.SysTaskConfigRepository;
import com.syx.comment.repository.SysTaskReleaseDepartmentRepository;
import com.syx.comment.repository.SysTaskReleaseRepository;
import com.syx.comment.utils.SqlEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSInput;

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
    public SysTaskRelease saveTaskReleaseInformation(SysTaskRelease sysTaskRelease) {
        sysTaskRelease.setTaskCreateTime(new Date());
        return sysTaskReleaseRepository.save(sysTaskRelease);
    }

    @Override
    public JSONObject getTaskReleaseInformation(String id, String pageNumber, String pageSize) {
        String selectSqlTotal = "SELECT * FROM sys_task_release a WHERE a.task_config_id = '" + id + "' ";
        String selectSql = "SELECT a.*,COUNT(b.id) AS dep_number,GROUP_CONCAT(b.dep_no) FROM " +
                "(SELECT * FROM sys_task_release a WHERE a.task_config_id = '" + id + "'  " +
                "ORDER BY a.task_create_time DESC  " + SqlEasy.limitPage(pageSize, pageNumber) + " ) a " +
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
}
