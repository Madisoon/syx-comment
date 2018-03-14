package com.syx.comment.module.other.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.module.other.service.SysExportService;
import com.syx.comment.module.other.service.SysRankService;
import com.syx.comment.module.task.service.TaskPostService;
import com.syx.comment.utils.DataExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 描述:
 * 导出的接口实现类
 *
 * @author Msater Zg
 * @create 2018-02-26 20:22
 */
@Service
public class SysExportServiceImpl implements SysExportService {

    @Autowired
    DataExport dataExport;

    @Autowired
    SysRankService sysRankService;

    @Autowired
    TaskPostService taskPostService;

    @Override
    public String rankDataExport(String packetNo, String startTime, String endTime, String type, String path) {
        StringBuilder url = new StringBuilder("");
        JSONArray jsonArray = new JSONArray();
        switch (type) {
            case "1":
                jsonArray = sysRankService.getDepMarkRankData(packetNo, startTime, endTime);
                url.append(dataExport.exportDepRankData(jsonArray, path, type));
                break;
            case "2":
                jsonArray = sysRankService.getDepTypeRankData(packetNo, startTime, endTime);
                url.append(dataExport.exportDepRankData(jsonArray, path, type));
                break;
            case "3":
                JSONObject jsonObject = sysRankService.getDepCountRankData(packetNo, startTime, endTime);
                url.append(dataExport.exportCountRankData(jsonObject, path));
                break;
            default:
                break;
        }
        return url.toString();
    }

    @Override
    public String taskDataExport(String userAccount, String searchData, String taskType, String taskStatus, String depNo, String filePath) {
        JSONObject jsonObject = taskPostService.getUserTaskInformation(userAccount, searchData, taskType, taskStatus, "1", "10", depNo);
        JSONArray jsonArray = jsonObject.getJSONArray("allTotal");
        return dataExport.exportCheckedTaskData(jsonArray, filePath);
    }
}
