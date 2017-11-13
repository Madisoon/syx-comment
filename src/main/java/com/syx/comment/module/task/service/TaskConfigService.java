package com.syx.comment.module.task.service;

import com.syx.comment.entity.SysTaskConfig;

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

    List<SysTaskConfig> getTaskConfigByPacketNo(String packetNo);
}
