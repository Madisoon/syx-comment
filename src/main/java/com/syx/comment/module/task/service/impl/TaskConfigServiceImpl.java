package com.syx.comment.module.task.service.impl;

import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.module.task.service.TaskConfigService;
import com.syx.comment.repository.SysTaskConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 任务配置的接口实现
 *
 * @author Msater Zg
 * @create 2017-11-13 19:29
 */
@Service
public class TaskConfigServiceImpl implements TaskConfigService {
    @Autowired
    SysTaskConfigRepository sysTaskConfigRepository;

    @Override
    public SysTaskConfig saveTaskConfigInformation(SysTaskConfig sysTaskConfig) {
        sysTaskConfig.setTaskTime(new Date());
        return sysTaskConfigRepository.save(sysTaskConfig);
    }

    @Override
    public List<SysTaskConfig> getTaskConfigByPacketNo(String packetNo) {
        return null;
    }
}
