package com.syx.comment.module.task.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.entity.SysTaskRelease;
import com.syx.comment.entity.SysTaskReleaseUser;

import java.util.List;

/**
 * 描述:
 * 任务配置的API
 *
 * @author Msater Zg
 * @create 2017-11-13 19:28
 */
public interface TaskConfigService {
    /**
     * 更新任务配置的内容
     *
     * @param sysTaskConfig
     * @return
     */
    SysTaskConfig saveTaskConfigInformation(SysTaskConfig sysTaskConfig);

    /**
     * 根据系统编号获取任务配置
     *
     * @param packetNo
     * @return
     */
    List<SysTaskConfig> getTaskConfigByPacketNo(String packetNo);

    /**
     * 更新发布任务的内容
     *
     * @param sysTaskRelease
     * @param taskDep
     * @return
     */
    SysTaskRelease saveTaskReleaseInformation(SysTaskRelease sysTaskRelease, String taskDep);

    /**
     * 分页得到任务的信息
     *
     * @param userAccount
     * @return
     */
    JSONArray getTaskReleaseInformation(String userAccount);

    /**
     * 根据id删除所有的配置的信息
     *
     * @param taskConfigId
     * @return JSONObject
     */
    JSONObject deleteTaskConfigInformation(String taskConfigId);

    /**
     * 根据id删除发布的任务
     *
     * @param taskId
     * @return
     */
    JSONObject deleteTaskInformation(String taskId);

    /**
     * 根据系统编号和部门编号获取所有的通知
     *
     * @param sysPacketNo
     * @param userName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    JSONObject getAllNoteInformation(String sysPacketNo, String userName, String pageSize, String pageNumber);

    /**
     * 标记已读或者未读消息
     *
     * @param type
     * @param taskReleaseId
     * @param userName
     * @return
     */
    JSONObject tabReadOrFinish(String type, String taskReleaseId, String userName);

    /**
     * 删除标记
     *
     * @param type
     * @param id
     * @return
     */
    JSONObject removeReadOrFinish(String type, String id);

    /**
     * 根据发布的任务，和部门编号获取任务的详情
     *
     * @param taskNumber
     * @param userAccount
     * @return
     */
    JSONObject getTaskReleaseByNumber(String taskNumber, String userAccount);

    /**
     * 发布任务
     * @param id
     * @return
     */
    SysTaskRelease updateTaskReleaseStatus(String id);
}
