package com.syx.comment.module.other.service;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysGuide;

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
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public JSONObject listSysLog(String pageNumber, String pageSize);

    /**
     * 判断是否看过指导页
     *
     * @param userAccount
     * @return
     */
    public SysGuide userIsGuide(String userAccount);

    /**
     * 插入用户的指导页数据
     *
     * @param userAccount
     * @return
     */
    public SysGuide saveUserAccountGuide(String userAccount);
}
