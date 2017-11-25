package com.syx.comment.module.task.service;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysTaskFinish;

import java.util.List;

/**
 * 描述:
 * 任务发布的API（包括统计）
 *
 * @author Msater Zg
 * @create 2017-11-19 20:28
 */
public interface TaskPostService {
    /**
     * 管理发布任务的信息
     *
     * @param sysTaskFinish
     * @return
     */
    SysTaskFinish saveTaskFinishInformation(SysTaskFinish sysTaskFinish);

    /**
     * 删除任务的信息
     *
     * @param taskFinishId
     * @return JSONObject
     */
    JSONObject deleteTaskFinishInformation(String taskFinishId);

    /**
     * 根据部门获取信息
     *
     * @param depNo
     * @param pageSize
     * @param pageNumber
     * @return JSONObject
     */
    JSONObject getDepTaskInformation(String depNo, String pageSize, String pageNumber);

    /**
     * 根据数据包获取审核信息
     *
     * @param sysPacketNo
     * @param taskStatus
     * @param pageSize
     * @param pageNumber
     * @return JSONObject
     */
    JSONObject getPacketInformation(String sysPacketNo, String taskStatus, String pageSize, String pageNumber);

    /**
     * 管理发布任务的信息
     *
     * @param sysTaskFinish
     * @return
     */
    SysTaskFinish saveTaskFinishInformationPart(SysTaskFinish sysTaskFinish, String userName);
}
