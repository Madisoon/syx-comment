package com.syx.comment.module.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysTaskFinish;
import com.syx.comment.module.task.service.TaskPostService;
import com.syx.comment.repository.SysTaskFinishRepository;
import com.syx.comment.utils.SqlEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 任务发布的接口实现（包括审核和一些统计）
 *
 * @author Msater Zg
 * @create 2017-11-19 20:29
 */
@Service
public class TaskPostServiceImpl implements TaskPostService {
    @Autowired
    SysTaskFinishRepository sysTaskFinishRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public SysTaskFinish saveTaskFinishInformation(SysTaskFinish sysTaskFinish) {
        sysTaskFinish.setTaskCreateTime(new Date());
        return sysTaskFinishRepository.save(sysTaskFinish);
    }

    @Override
    public JSONObject deleteTaskFinishInformation(String taskFinishId) {
        String[] taskFinishIdS = taskFinishId.split(",");
        int taskFinishIdSLen = taskFinishIdS.length;
        List<SysTaskFinish> list = new ArrayList<>();
        for (int i = 0; i < taskFinishIdSLen; i++) {
            SysTaskFinish sysTaskFinish = new SysTaskFinish();
            sysTaskFinish.setId(Long.parseLong(taskFinishIdS[i]));
            list.add(sysTaskFinish);
        }
        JSONObject jsonObject = new JSONObject();
        try {
            sysTaskFinishRepository.delete(list);
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getDepTaskInformation(String depNo, String pageSize, String pageNumber) {

        String sqlTotal = " SELECT a.*,c.task_config_name FROM sys_task_finish a , sys_user b ,sys_task_config c " +
                " WHERE a.task_creater = b.user_name AND b.user_dep = ? AND a.task_type = c.id AND c.task_status = '1' " +
                "ORDER BY a.task_create_time DESC ";
        String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
        List<Map<String, String>> list = baseDao.rawQuery(sqlTotal, new String[]{depNo});
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{depNo}));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", list.size());
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @Override
    public JSONObject getPacketInformation(String sysPacketNo, String taskStatus, String pageSize, String pageNumber) {
        String sqlTotal = "SELECT a.*,c.dep_name,d.task_config_name FROM sys_task_finish a , sys_user b,  " +
                "sys_department c, sys_task_config d " +
                "WHERE a.task_creater = b.user_name AND b.user_dep = c.dep_no AND a.task_type = d.id  " +
                "AND a.task_packet_no = ? ";
        String statusWhere = "";
        switch (taskStatus) {
            case "0":
                statusWhere = " AND ( a.task_status = '0' OR  a.task_status = '1' )";
                break;
            default:
                statusWhere = " AND  a.task_status = '" + taskStatus + "' ";
                break;
        }
        sqlTotal += statusWhere;
        sqlTotal += " ORDER BY a.task_create_time DESC ";
        String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
        List<Map<String, String>> list = baseDao.rawQuery(sqlTotal, new String[]{sysPacketNo});
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{sysPacketNo}));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", list.size());
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @Override
    public SysTaskFinish saveTaskFinishInformationPart(SysTaskFinish sysTaskFinish, String userName) {
        SysTaskFinish sysTaskFinishData = sysTaskFinishRepository.findOne(sysTaskFinish.getId());
        String taskFeedback = sysTaskFinish.getTaskFeedback();
        int taskStatus = sysTaskFinish.getTaskMark();
        sysTaskFinishData.setTaskMark(taskStatus);
        sysTaskFinishData.setTaskStatus(sysTaskFinish.getTaskStatus());
        if (!"".equals(taskFeedback)) {
            sysTaskFinishData.setTaskFeedback(taskFeedback);
        }
        int taskReceiveStatus = 1;
        if (taskStatus != taskReceiveStatus) {
            sysTaskFinishData.setTaskCheckPeople(userName);
            sysTaskFinishData.setTaskCheckTime(new Date());
        }
        return sysTaskFinishRepository.save(sysTaskFinishData);
    }
}
