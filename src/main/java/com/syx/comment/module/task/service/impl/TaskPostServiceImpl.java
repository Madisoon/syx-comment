package com.syx.comment.module.task.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.*;
import com.syx.comment.module.task.service.TaskPostService;
import com.syx.comment.repository.*;
import com.syx.comment.utils.DataExport;
import com.syx.comment.utils.SqlEasy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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

    @Autowired
    DataExport dataExport;

    @Autowired
    SysTaskDiscussRepository sysTaskDiscussRepository;

    @Autowired
    SysNoticeRepository sysNoticeRepository;

    @Autowired
    SysTaskReleaseRepository sysTaskReleaseRepository;

    @Autowired
    SysFinishTabRepository sysFinishTabRepository;

    // jpa 使用原生sql语句
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SysTaskFinish saveTaskFinishInformation(SysTaskFinish sysTaskFinish) {
        sysTaskFinish.setGmtCreate(new Date());
        return sysTaskFinishRepository.save(sysTaskFinish);
    }

    @Override
    public JSONObject deleteTaskFinishInformation(String taskFinishId) {
        String[] taskFinishIdS = taskFinishId.split(",");
        int taskFinishIdSLen = taskFinishIdS.length;
        List<SysTaskFinish> list = new ArrayList<>();
        for (int i = 0; i < taskFinishIdSLen; i++) {
            Long taskId = Long.parseLong(taskFinishIdS[i]);
            SysTaskFinish sysTaskFinish = new SysTaskFinish();
            sysTaskFinish.setId(taskId);
            list.add(sysTaskFinish);
            List<SysTaskDiscuss> listDisCuss = sysTaskDiscussRepository.findSysTaskDiscussByTaskId(taskId);
            sysTaskDiscussRepository.delete(listDisCuss);
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
    public JSONObject getUserTaskInformation(String userAccount, String searchData, String taskType,
                                             String taskStatus, String pageSize, String pageNumber, String depNo) {
        JSONObject jsonObject = new JSONObject();
        if ("{}".equals(searchData)) {
            String sqlTotal = " SELECT a.*,GROUP_CONCAT(b.discuss_content) AS discuss_contents, " +
                    " GROUP_CONCAT(b.user_account) AS user_accounts ,GROUP_CONCAT(b.gmt_create) gmt_creates " +
                    " FROM (SELECT a.*,c.task_config_name,c.task_mark AS max_mark,d.user_account AS post_account,d.task_name AS post_task_name,c.task_color  ";
            if ("".equals(depNo)) {
                if ("1".equals(taskType)) {
                    // 用户自己看
                    sqlTotal += " FROM ( SELECT * FROM sys_task_finish a WHERE a.task_release_id IN  " +
                            "(SELECT task_release_id FROM  sys_task_finish a  " +
                            "WHERE a.user_account = ?) ) a , sys_task_config c,sys_task_release d WHERE a.user_account <> ''  ";
                } else {
                    // 审核的人员看
                    sqlTotal += "FROM sys_task_finish a , sys_task_config c,sys_task_release d  WHERE d.user_account = ?  ";
                }
            } else {
                sqlTotal += "FROM sys_task_finish a , sys_task_config c,sys_task_release d ," +
                        "sys_user e WHERE a.user_account = e.user_account AND e.user_dep = ? ";
            }
            sqlTotal += " AND a.task_type = c.id AND a.task_status = ? AND a.task_release_id = d.id " +
                    " ) a LEFT JOIN sys_task_discuss b " +
                    " ON a.id = b.task_id GROUP BY a.id ORDER BY a.is_stick DESC ,a.gmt_create DESC  ";
            String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
            String param = "";
            if ("".equals(depNo)) {
                param = userAccount;
            } else {
                param = depNo;
            }
            List<Map<String, String>> list = baseDao.rawQuery(sqlTotal, new String[]{param, taskStatus});
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{param, taskStatus}));
            jsonObject.put("total", list.size());
            jsonObject.put("data", jsonArray);
            JSONArray allTotal = (JSONArray) JSON.toJSON(list);
            jsonObject.put("allTotal", allTotal);
        } else {
            JSONObject jsonObjectData = JSON.parseObject(searchData);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(" SELECT a.*,GROUP_CONCAT(b.discuss_content) AS discuss_contents, " +
                    " GROUP_CONCAT(b.user_account) AS user_accounts ,GROUP_CONCAT(b.gmt_create) gmt_creates " +
                    " FROM (SELECT a.*,c.task_config_name,c.task_mark AS max_mark,d.user_account AS post_account,d.task_name AS post_task_name, c.task_color ");
            if ("".equals(depNo)) {
                if ("1".equals(taskType)) {
                    // 用户自己看
                    stringBuffer.append(" FROM ( SELECT * FROM sys_task_finish a WHERE a.task_release_id IN  " +
                            "(SELECT task_release_id FROM  sys_task_finish a  " +
                            "WHERE a.user_account = ?) ) a , sys_task_config c,sys_task_release d WHERE a.user_account <> ''  ");
                } else {
                    // 审核的人员看
                    stringBuffer.append("FROM sys_task_finish a , sys_task_config c,sys_task_release d  WHERE d.user_account = ?  ");
                }
            } else {
                stringBuffer.append("FROM sys_task_finish a , sys_task_config c,sys_task_release d ," +
                        "sys_user e WHERE a.user_account = e.user_account AND e.user_dep = ? ");
            }
            Set set = jsonObjectData.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = jsonObjectData.getString(key);
                switch (key) {
                    case "startTime":
                        stringBuffer.append(" AND a.gmt_create > '" + value + "' ");
                        break;
                    case "endTime":
                        stringBuffer.append(" AND a.gmt_create < '" + value + "' ");
                        break;
                    case "type":
                        stringBuffer.append(" AND a.task_type = " + value);
                        break;
                    case "keyWord":
                        stringBuffer.append(" AND ( a.task_explain LIKE '%" + value + "%' OR  a.task_content LIKE '%" + value + "%' " +
                                " OR a.task_link LIKE '%" + value + "%'   " +
                                " OR a.task_name LIKE '%" + value + "%' OR a.task_nick_name LIKE '%" + value + "%' " +
                                " OR a.task_title LIKE '%" + value + "%' OR a.task_site LIKE '%" + value + "%' " +
                                " OR a.task_tag LIKE '%" + value + "%' )");
                        break;
                    default:
                        break;
                }
            }
            stringBuffer.append(" AND a.task_type = c.id AND a.task_status = ?  AND a.task_release_id = d.id " +
                    " ) a LEFT JOIN sys_task_discuss b " +
                    " ON a.id = b.task_id GROUP BY a.id ORDER BY a.is_stick DESC ,a.gmt_create DESC  ");
            System.out.println(stringBuffer.toString());
            String param = "";
            if ("".equals(depNo)) {
                param = userAccount;
            } else {
                param = depNo;
            }
            String sqlPage = stringBuffer.toString() + SqlEasy.limitPage(pageSize, pageNumber);
            List<Map<String, String>> list = baseDao.rawQuery(stringBuffer.toString(), new String[]{param, taskStatus});
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{param, taskStatus}));
            jsonObject.put("total", list.size());
            jsonObject.put("data", jsonArray);
            JSONArray allTotal = (JSONArray) JSON.toJSON(list);
            jsonObject.put("allTotal", allTotal);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getPacketInformation(String sysPacketNo, String taskStatus, String pageSize, String pageNumber) {
        String sqlTotal = "SELECT a.*,c.dep_name,d.task_config_name,c.task_mark AS max_mark,d.task_mark as task_config_mark FROM sys_task_finish a , sys_user b,  " +
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
        double taskStatus = sysTaskFinish.getTaskMark();
        sysTaskFinishData.setTaskMark(taskStatus);
        sysTaskFinishData.setTaskStatus(sysTaskFinish.getTaskStatus());
        int taskReceiveStatus = 1;
        if (taskStatus != taskReceiveStatus) {
            sysTaskFinishData.setCheckAccount(userName);
            sysTaskFinishData.setTaskCheckTime(new Date());
        }
        return sysTaskFinishRepository.save(sysTaskFinishData);
    }

    @Override
    public JSONObject getTaskRankInformation(String taskPacketNo, String rankType, String searchData, String pageSize, String pageNumber) {
        List<String> list = new ArrayList<>(16);
        if ("{}".equals(searchData)) {
            list.add("SELECT a.*,b.user_nick_name,c.dep_name, d.task_config_name,SUM(a.task_mark) AS total_number ");
            list.add("FROM sys_task_finish a ,sys_user b ,sys_department c ,sys_task_config d ");
            list.add("WHERE a.task_creater = b.user_name AND b.user_dep = c.dep_no AND a.task_type = d.id AND a.task_status = '2' ");
            list.add("AND a.task_packet_no = ? ");
            switch (rankType) {
                case "1":
                    list.add(" GROUP BY b.user_dep ");
                    break;
                case "2":
                    list.add(" GROUP BY a.task_creater  ");
                    break;
                case "3":
                    list.add(" GROUP BY a.task_type ");
                    break;
                default:
                    list.add("");
                    break;
            }
            list.add(" ORDER BY total_number DESC ");
            String sqlTotal = StringUtils.join(list, "");
            String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
            List<Map<String, String>> listTotal = baseDao.rawQuery(sqlTotal, new String[]{taskPacketNo});
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{taskPacketNo}));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total", listTotal.size());
            jsonObject.put("data", jsonArray);
            return jsonObject;
        } else {
            JSONObject jsonObject = JSON.parseObject(searchData);
            String sqlChoose = "SELECT a.id, a.user_nick_name,a.dep_name, a.task_config_name,SUM(a.task_mark) AS total_number FROM (SELECT a.*,b.user_nick_name,b.user_dep,c.dep_name,d.task_config_name FROM sys_task_finish a ,sys_user b ," +
                    "sys_department c ,sys_task_config d   " +
                    "WHERE a.task_creater = b.user_name AND b.user_dep = c.dep_no AND a.task_type = d.id ) a  " +
                    "WHERE a.task_status ='2' AND a.task_packet_no = '" + taskPacketNo + "' ";
            Set set = jsonObject.keySet();
            Iterator<String> iterator = set.iterator();
            while (iterator.hasNext()) {
                String value = iterator.next();
                if ("startTime".equals(value)) {
                    sqlChoose += " AND a.task_create_time > '" + jsonObject.getString(value) + "' ";
                } else if ("endTime".equals(value)) {
                    sqlChoose += " AND a.task_create_time < '" + jsonObject.getString(value) + "' ";
                } else {
                    sqlChoose += " AND a." + value + " LIKE '%" + jsonObject.getString(value) + "%' ";
                }
            }
            switch (rankType) {
                case "1":
                    sqlChoose += " GROUP BY a.task_creater ";
                    break;
                case "2":
                    sqlChoose += " GROUP BY a.user_dep ";
                    break;
                case "3":
                    sqlChoose += " GROUP BY a.task_type ";
                    break;
                default:
                    sqlChoose += "";
                    break;
            }
            list.add(" ORDER BY total_number DESC ");
            String sqlPage = sqlChoose + SqlEasy.limitPage(pageSize, pageNumber);
            List<Map<String, String>> listTotal = baseDao.rawQuery(sqlChoose, null);
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, null));
            JSONObject jsonObjectData = new JSONObject();
            jsonObjectData.put("total", listTotal.size());
            jsonObjectData.put("data", jsonArray);
            return jsonObjectData;
        }
    }

    @Override
    public JSONObject getTaskChooseInformation(String sysPacketNo, String searchData, String pageSize, String pageNumber) {
        JSONObject jsonObject = JSON.parseObject(searchData);
        String sqlChoose = "SELECT * FROM (SELECT a.*,b.user_nick_name,b.user_dep,c.dep_name,d.task_config_name,d.task_mark as task_config_mark FROM sys_task_finish a ,sys_user b ," +
                "sys_department c ,sys_task_config d   " +
                "WHERE a.task_creater = b.user_name AND b.user_dep = c.dep_no AND a.task_type = d.id ) a  " +
                "WHERE a.task_status ='2' AND a.task_packet_no = '" + sysPacketNo + "' ";
        Set set = jsonObject.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if ("startTime".equals(value)) {
                sqlChoose += " AND a.task_create_time > '" + jsonObject.getString(value) + "' ";
            } else if ("endTime".equals(value)) {
                sqlChoose += " AND a.task_create_time < '" + jsonObject.getString(value) + "' ";
            } else {
                sqlChoose += " AND a." + value + " LIKE '%" + jsonObject.getString(value) + "%' ";
            }
        }
        String sqlPage = sqlChoose + SqlEasy.limitPage(pageSize, pageNumber);
        List<Map<String, String>> listTotal = baseDao.rawQuery(sqlChoose, null);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, null));
        JSONObject jsonObjectData = new JSONObject();
        jsonObjectData.put("total", listTotal.size());
        jsonObjectData.put("data", jsonArray);
        return jsonObjectData;
    }

    @Override
    public String exportExcelTaskRank(String sysPacketNo, String rankType, String searchData, String filePath) {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = getTaskRankInformation(sysPacketNo, rankType, searchData, "1000", "1");
        jsonArray = jsonObject.getJSONArray("data");
        return dataExport.exportCustomerData(jsonArray, rankType, filePath);
    }

    @Override
    public SysTaskDiscuss saveTaskFinishDisCuss(SysTaskDiscuss sysTaskDiscuss) {
        sysTaskDiscuss.setGmtCreate(new Date());
        sysTaskDiscuss.setGmtModified(new Date());
        sysTaskDiscuss = sysTaskDiscussRepository.save(sysTaskDiscuss);
        SysTaskFinish sysTaskFinish = sysTaskFinishRepository.getOne(sysTaskDiscuss.getTaskId());
        SysTaskRelease sysTaskRelease = sysTaskReleaseRepository.getOne(sysTaskFinish.getTaskReleaseId());
        String receiveUser = sysTaskFinish.getUserAccount().equals(sysTaskDiscuss.getUserAccount()) ? sysTaskRelease.getUserAccount() : sysTaskFinish.getUserAccount();
        SysNotice sysNotice = new SysNotice();
        sysNotice.setGmtCreate(new Date());
        sysNotice.setGmtModified(new Date());
        sysNotice.setNoticeContent("您有一条新的评论，内容为：" + sysTaskDiscuss.getDiscussContent());
        sysNotice.setNoticeReceive(receiveUser);
        sysNotice.setNoticeType(1);
        sysNotice.setNoticeUrl("");
        sysNotice.setUserAccount(sysTaskDiscuss.getUserAccount());
        sysNoticeRepository.save(sysNotice);
        return sysTaskDiscuss;
    }

    @Override
    public SysTaskFinish saveTaskFinishMark(String taskId, String taskMark, String userAccount) {
        SysTaskFinish sysTaskFinish = sysTaskFinishRepository.findOne(Long.parseLong(taskId));
        String userAccountPost = sysTaskFinish.getUserAccount();
        Long releaseId = sysTaskFinish.getTaskReleaseId();
        SysFinishTab sysFinishTab = new SysFinishTab();
        sysFinishTab.setTaskId(releaseId);
        sysFinishTab.setUserAccount(userAccountPost);
        sysFinishTab.setGmtCreate(new Date());
        sysFinishTab.setGmtModified(new Date());
        sysFinishTabRepository.save(sysFinishTab);
        sysTaskFinish.setGmtModified(new Date());
        sysTaskFinish.setTaskMark(Double.parseDouble(taskMark));
        sysTaskFinish.setCheckAccount(userAccount);
        sysTaskFinish.setTaskStatus(1);
        sysTaskFinishRepository.save(sysTaskFinish);
        SysNotice sysNotice = new SysNotice();
        sysNotice.setGmtCreate(new Date());
        sysNotice.setGmtModified(new Date());
        sysNotice.setNoticeContent("您有一个任务被审核打分了，分数为：" + taskMark);
        sysNotice.setNoticeReceive(sysTaskFinish.getUserAccount());
        sysNotice.setNoticeType(1);
        sysNotice.setNoticeUrl("");
        sysNotice.setUserAccount(userAccount);
        sysNoticeRepository.save(sysNotice);
        return sysTaskFinish;
    }

    @Override
    public SysTaskFinish saveTaskFinishStick(String taskId, String type) {
        SysTaskFinish sysTaskFinish = sysTaskFinishRepository.findOne(Long.parseLong(taskId));
        if ("1".equals(type)) {
            // 標星
            int isStar = sysTaskFinish.getIsStar();
            sysTaskFinish.setIsStar(isStar == 1 ? 0 : 1);
            sysTaskFinish.setGmtModified(new Date());
            sysTaskFinish = sysTaskFinishRepository.save(sysTaskFinish);
        } else {
            // 固定
            int isStick = sysTaskFinish.getIsStick();
            sysTaskFinish.setIsStick(isStick == 1 ? 0 : 1);
            sysTaskFinish.setGmtModified(new Date());
            sysTaskFinish = sysTaskFinishRepository.save(sysTaskFinish);
        }
        return sysTaskFinish;
    }
}
