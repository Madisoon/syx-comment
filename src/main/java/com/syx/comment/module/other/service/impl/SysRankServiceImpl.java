package com.syx.comment.module.other.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.module.other.service.SysRankService;
import com.syx.comment.repository.SysDepartmentRepository;
import com.syx.comment.utils.DateOrTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 统计的接口实现
 *
 * @author Msater Zg
 * @create 2018-02-13 13:05
 */
@Service
public class SysRankServiceImpl implements SysRankService {
    @Autowired
    BaseDao baseDao;

    @Autowired
    SysDepartmentRepository sysDepartmentRepository;

    @Autowired
    DateOrTimeUtil dateOrTimeUtil;

    @Override
    public JSONArray getDepMarkRankData(String packetNo, String startTime, String endTime) {
        String markRank = "SELECT a.dep_name,SUM(c.task_mark) AS task_marks  " +
                "FROM sys_department a, sys_user b, sys_task_finish c  " +
                "WHERE a.packet_no = ?  " +
                "AND a.dep_no = b.user_dep  AND b.user_account = c.user_account  " +
                "AND c.task_status=1 AND c.gmt_create>=? AND c.gmt_create<=? " +
                "GROUP BY  a.`id` ORDER BY task_marks DESC ";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(markRank, new String[]{packetNo, startTime, endTime}));
        return jsonArray;
    }

    @Override
    public JSONObject getDepCountRankData(String packetNo, String startTime, String endTime) {
        JSONArray jsonArrayData = getDepNumberRankData(packetNo, startTime, endTime);
        JSONArray jsonArrayName = new JSONArray();
        JSONArray jsonArrayTime = new JSONArray();
        List listTime = dateOrTimeUtil.computingTime(startTime, endTime);
        for (int i = 0; i < listTime.size(); i++) {
            jsonArrayTime.add(listTime.get(i));
        }
        List<SysDepartment> list = sysDepartmentRepository.findSysDepartmentByPacketNo(packetNo);
        for (int i = 0, len = list.size(); i < len; i++) {
            SysDepartment sysDepartment = list.get(i);
            jsonArrayName.add(sysDepartment.getDepName());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", jsonArrayName);
        jsonObject.put("data", jsonArrayData);
        jsonObject.put("time", jsonArrayTime);
        return jsonObject;
    }

    @Override
    public JSONArray getDepTypeRankData(String packetNo, String startTime, String endTime) {
        String typeRank = "SELECT d.task_config_name,COUNT(c.id) AS task_types " +
                "FROM sys_department a, sys_user b, sys_task_finish c ,sys_task_config d " +
                "WHERE a.packet_no = ?  AND c.task_type = d.id " +
                "AND a.dep_no = b.user_dep  AND b.user_account = c.user_account " +
                "AND c.task_status=1 AND c.gmt_create>=? AND c.gmt_create<=? " +
                "GROUP BY  c.task_type ";
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(typeRank, new String[]{packetNo, startTime, endTime}));
        return jsonArray;
    }

    @Override
    public JSONArray getDepNumberRankData(String packetNo, String startTime, String endTime) {
        List listTime = dateOrTimeUtil.computingTime(startTime, endTime);
        List<SysDepartment> list = sysDepartmentRepository.findSysDepartmentByPacketNo(packetNo);
        JSONArray jsonArrayReturn = new JSONArray();
        for (int i = 0, len = list.size(); i < len; i++) {
            SysDepartment sysDepartment = list.get(i);
            StringBuffer sql = new StringBuffer();
            for (int n = 0, listTimeLen = listTime.size(); n < listTimeLen; n++) {
                if (n == 0) {
                    sql.append("SELECT COUNT(*) AS total FROM  sys_department a,sys_user b ,sys_task_finish c  " +
                            "WHERE a.dep_no = " + sysDepartment.getDepNo() + " AND c.gmt_create LIKE '%" + listTime.get(n) + "%' " +
                            "AND a.dep_no = b.user_dep AND b.user_account = c.user_account");
                } else {
                    sql.append(" UNION ALL " +
                            "SELECT COUNT(*) AS total FROM  sys_department a,sys_user b ,sys_task_finish c  " +
                            "WHERE a.dep_no = " + sysDepartment.getDepNo() + " AND c.gmt_create LIKE '%" + listTime.get(n) + "%' " +
                            "AND a.dep_no = b.user_dep AND b.user_account = c.user_account");
                }
            }
            JSONObject jsonObject = new JSONObject();
            JSONArray data = new JSONArray();
            List<Map<String, String>> dataMap = baseDao.rawQuery(sql.toString());
            for (int m = 0; m < dataMap.size(); m++) {
                Map map = dataMap.get(m);
                data.add(map.get("total"));
            }
            jsonObject.put("name", sysDepartment.getDepName());
            jsonObject.put("type", "line");
            jsonObject.put("stack", "总量");
            jsonObject.put("areaStyle", "{normal: {}}");
            jsonObject.put("data", data);
            jsonArrayReturn.add(jsonObject);
        }
        return jsonArrayReturn;
    }
}
