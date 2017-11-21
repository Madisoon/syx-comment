package com.syx.comment.module.task.service;

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
    SysTaskFinish saveTaskFinishInformation(SysTaskFinish sysTaskFinish);

    void deleteTaskFinishInformation(String taskFinishId);
}
