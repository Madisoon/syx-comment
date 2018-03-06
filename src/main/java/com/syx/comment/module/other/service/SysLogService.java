package com.syx.comment.module.other.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 * 日志的API
 *
 * @author Msater Zg
 * @create 2018-03-05 19:13
 */
public interface SysLogService {
    /**
     * 获取系统日志
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public JSONObject listSysLog(String pageNumber, String pageSize);
}
