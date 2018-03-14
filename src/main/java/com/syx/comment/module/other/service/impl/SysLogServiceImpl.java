package com.syx.comment.module.other.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.entity.SysGuide;
import com.syx.comment.module.other.service.SysLogService;
import com.syx.comment.repository.SysGuideRepository;
import com.syx.comment.utils.SqlEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    SysGuideRepository sysGuideRepository;

    @Override
    public JSONObject listSysLog(String pageNumber, String pageSize) {
        String sql = "SELECT * FROM sys_log ORDER BY gmt_create DESC ";
        StringBuilder sqlPage = new StringBuilder();
        sqlPage.append(sql).append(SqlEasy.limitPage(pageSize, pageNumber));
        List listTotal = baseDao.rawQuery(sql);
        JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage.toString()));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", listTotal.size());
        // 用注意回收
        listTotal.clear();
        jsonObject.put("data", jsonArray);
        return jsonObject;
    }

    @Override
    public SysGuide userIsGuide(String userAccount) {
        return sysGuideRepository.findSysGuideByUserAccount(userAccount);
    }

    @Override
    public SysGuide saveUserAccountGuide(String userAccount) {
        SysGuide sysGuide = new SysGuide();
        sysGuide.setUserAccount(userAccount);
        sysGuide.setGmtCreate(new Date());
        sysGuide.setGmtModified(new Date());
        return sysGuideRepository.save(sysGuide);
    }
}
