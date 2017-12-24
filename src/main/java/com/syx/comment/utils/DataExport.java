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
    public String exportCustomerData(JSONArray jsonArray, String rankType) {
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
            documentHandler.createDoc(maps, "C:/dummyPath/" + fileName, fileFtl);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}
