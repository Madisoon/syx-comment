package com.syx.comment.module.other.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 * 统计的接口类
 *
 * @author Msater Zg
 * @create 2018-02-13 13:05
 */
public interface SysRankService {
    /**
     * 获取整个部门的分数统计数据
     *
     * @param packetNo
     * @param startTime
     * @param endTime
     * @return
     */
    JSONArray getDepMarkRankData(String packetNo, String startTime, String endTime);

    /**
     * 获取系统部门提交数量
     *
     * @param packetNo
     * @param startTime
     * @param endTime
     * @return
     */
    JSONObject getDepCountRankData(String packetNo, String startTime, String endTime);

    /**
     * 获取系统类型提交的数量
     *
     * @param packetNo
     * @param startTime
     * @param endTime
     * @return
     */
    JSONArray getDepTypeRankData(String packetNo, String startTime, String endTime);

    /**
     * 获取系统任务数量提交的图表
     *
     * @param packetNo
     * @param startTime
     * @param endTime
     * @return
     */
    JSONArray getDepNumberRankData(String packetNo, String startTime, String endTime);
}
