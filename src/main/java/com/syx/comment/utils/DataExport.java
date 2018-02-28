package com.syx.comment.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Msater Zg
 */
@Component
public class DataExport {
    public String exportCustomerData(JSONArray jsonArray, String rankType, String filePath) {
        List list = new ArrayList();
        DocumentHandler documentHandler = new DocumentHandler();
        int jsonArrayLen = jsonArray.size();
        String rankName = "";
        for (int i = 0; i < jsonArrayLen; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map map = new HashMap(16);
            map.put("index", i + 1);
            switch (rankType) {
                case "1":
                    map.put("name", jsonObject.getString("dep_name"));
                    rankName = "部门名称";
                    break;
                case "2":
                    map.put("name", jsonObject.getString("user_nick_name"));
                    rankName = "部门人员";
                    break;
                case "3":
                    map.put("name", jsonObject.getString("task_config_name"));
                    rankName = "任务类型";
                    break;
                default:
                    break;
            }
            map.put("totalNumber", jsonObject.getString("total_number"));
            list.add(map);
        }

        Map maps = new HashMap(16);
        maps.put("rankName", rankName);
        maps.put("rankList", list);
        String longTime = String.valueOf(System.currentTimeMillis());
        String fileName = longTime + ".xls";
        String fileFtl = "rankFtl.ftl";
        try {
            documentHandler.createDoc(maps, filePath + fileName, fileFtl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 格式化获取的数据
     *
     * @param jsonArray
     * @param type
     * @return
     */
    public List getDepRankList(JSONArray jsonArray, String type) {
        List list = new ArrayList();
        for (int i = 0, jsonArrayLen = jsonArray.size(); i < jsonArrayLen; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map map = new HashMap(16);
            map.put("index", i + 1);
            if ("1".equals(type)) {
                map.put("name", jsonObject.getString("dep_name"));
                map.put("number", jsonObject.getString("task_marks"));
            } else {
                map.put("name", jsonObject.getString("task_config_name"));
                map.put("number", jsonObject.getString("task_types"));
            }
            list.add(map);
        }
        return list;
    }

    public String exportDepRankData(JSONArray jsonArray, String filePath, String type) {
        List list = getDepRankList(jsonArray, type);
        DocumentHandler documentHandler = new DocumentHandler();
        String titleName = "";
        String contentName = "";
        String depRankType = "1";
        if (depRankType.equals(type)) {
            titleName = "部门名称";
            contentName = "部门总分";
        } else {
            titleName = "类型名称";
            contentName = "类型数量";
        }
        Map maps = new HashMap(16);
        maps.put("titleName", titleName);
        maps.put("contentName", contentName);
        maps.put("rankList", list);
        String longTime = String.valueOf(System.currentTimeMillis());
        String fileName = longTime + ".xls";
        String fileFtl = "depAndTypeRankTemplet.ftl";
        try {
            documentHandler.createDoc(maps, filePath + fileName, fileFtl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String exportCountRankData(JSONObject jsonObject, String filePath) {
        DocumentHandler documentHandler = new DocumentHandler();
        Map maps = new HashMap(16);
        List list = new ArrayList();
        List listDep = new ArrayList();
        JSONArray jsonArrayDep = jsonObject.getJSONArray("name");
        int jsonArrayDepLen = jsonArrayDep.size();
        for (int i = 0; i < jsonArrayDepLen; i++) {
            listDep.add(jsonArrayDep.get(i));
        }
        JSONArray jsonArrayTime = jsonObject.getJSONArray("time");
        JSONArray jsonArrayData = jsonObject.getJSONArray("data");
        int jsonArrayTimeLen = jsonArrayTime.size();
        for (int i = 0; i < jsonArrayTimeLen; i++) {
            Map map = new HashMap(16);
            map.put("time", jsonArrayTime.get(i));
            map.put("dep", listDep);
            List listNumber = new ArrayList();
            for (int n = 0; n < jsonArrayDepLen; n++) {
                JSONObject jsonObjectData = jsonArrayData.getJSONObject(n);
                JSONArray jsonArray = jsonObjectData.getJSONArray("data");
                listNumber.add(jsonArray.get(i));
            }
            map.put("number", listNumber);
            list.add(map);
        }

        maps.put("countRankList", list);
        String longTime = String.valueOf(System.currentTimeMillis());
        String fileName = longTime + ".xls";
        String fileFtl = "countRankTemplet.ftl";
        try {
            documentHandler.createDoc(maps, filePath + fileName, fileFtl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String exportCheckedTaskData(JSONArray jsonArray, String filePath) {
        List list = new ArrayList();
        DocumentHandler documentHandler = new DocumentHandler();
        for (int i = 0, jsonArrayLen = jsonArray.size(); i < jsonArrayLen; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Map map = new HashMap(16);
            map.put("taskName",jsonObject.getString("task_name"));
            map.put("taskNumber",jsonObject.getString("task_number"));
            map.put("userAccount",jsonObject.getString("user_account"));
            map.put("taskMark",jsonObject.getString("task_mark"));
            map.put("taskLink",jsonObject.getString("task_link"));
            map.put("taskTitle",jsonObject.getString("task_title"));
            map.put("taskContent",jsonObject.getString("task_content"));
            map.put("taskExplain",jsonObject.getString("task_explain"));
            map.put("taskImageUrl",jsonObject.getString("task_image_url"));
            map.put("taskFileUrl",jsonObject.getString("task_file_url"));
            list.add(map);
        }
        Map maps = new HashMap(16);
        maps.put("taskList", list);
        String longTime = String.valueOf(System.currentTimeMillis());
        String fileName = longTime + ".xls";
        String fileFtl = "taskExportTemplet.ftl";
        try {
            documentHandler.createDoc(maps, filePath + fileName, fileFtl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
