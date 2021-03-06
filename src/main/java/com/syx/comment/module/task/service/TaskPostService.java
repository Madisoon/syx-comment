package com.syx.comment.module.task.service;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysTaskDiscuss;
import com.syx.comment.entity.SysTaskFinish;

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
     * @param userAccount
     * @param searchData
     * @param taskType
     * @param taskStatus
     * @param pageSize
     * @param pageNumber
     * @param depNo
     * @return JSONObject
     */
    JSONObject getUserTaskInformation(String userAccount, String searchData,
                                      String taskType, String taskStatus,
                                      String pageSize, String pageNumber, String depNo);

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
     * @param userName
     * @return SysTaskFinish
     */
    SysTaskFinish saveTaskFinishInformationPart(SysTaskFinish sysTaskFinish, String userName);

    /**
     * 得到排序的信息
     *
     * @param taskPacketNo
     * @param rankType
     * @param searchData
     * @param pageSize
     * @param pageNumber
     * @return JSONObject
     */
    JSONObject getTaskRankInformation(String taskPacketNo, String rankType, String searchData, String pageSize, String pageNumber);

    /**
     * 根据筛选得到已审核的任务的信息
     *
     * @param sysPacketNo
     * @param searchData
     * @param pageSize
     * @param pageNumber
     * @return JSONObject
     */
    JSONObject getTaskChooseInformation(String sysPacketNo, String searchData, String pageSize, String pageNumber);

    /**
     * 导出任务的排名数据
     *
     * @param sysPacketNo
     * @param rankType
     * @param searchData
     * @param filePath
     * @return
     */
    String exportExcelTaskRank(String sysPacketNo, String rankType, String searchData, String filePath);

    /**
     * 添加评论
     *
     * @param sysTaskDiscuss
     * @return
     */
    SysTaskDiscuss saveTaskFinishDisCuss(SysTaskDiscuss sysTaskDiscuss);

    /**
     * 审核人员评论任务
     *
     * @param taskId
     * @param taskMark
     * @param userAccount
     * @return
     */
    SysTaskFinish saveTaskFinishMark(String taskId, String taskMark, String userAccount);

    /**
     * 添加Stick
     *
     * @param taskId
     * @param postType
     * @return
     */
    SysTaskFinish saveTaskFinishStick(String taskId, String postType);
}
