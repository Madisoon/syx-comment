package com.syx.comment.module.other.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.module.other.service.SysLogService;
import com.syx.comment.utils.SqlEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 * 日志的接口实现
 *
 * @author Msater Zg
 * @create 2018-03-05 19:13
 */
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    BaseDao baseDao;

    @Override
    public JSONObject listSysLog(String pageNumber, String pageSize) {
        String sql = "SELECT * FROM sys_log ORDER BY gmt_create DESC ";
        String sqlPage = sql + SqlEasy.limitPage(pageSize, pageNumber);
        List listTotal = baseDao.rawQuery(sql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", listTotal.size());
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }
}
