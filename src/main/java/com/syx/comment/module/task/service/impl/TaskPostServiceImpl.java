package com.syx.comment.module.task.service.impl;

import com.syx.comment.entity.SysTaskFinish;
import com.syx.comment.module.task.service.TaskPostService;
import com.syx.comment.repository.SysTaskFinishRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 任务发布的接口实现（包括审核和一些统计）
 *
 * @author Msater Zg
 * @create 2017-11-19 20:29
 */
public class TaskPostServiceImpl implements TaskPostService {
    @Autowired
    SysTaskFinishRepository sysTaskFinishRepository;

    @Override
    public SysTaskFinish saveTaskFinishInformation(SysTaskFinish sysTaskFinish) {
        return sysTaskFinishRepository.save(sysTaskFinish);
    }

    @Override
    public void deleteTaskFinishInformation(String taskFinishId) {
        String[] taskFinishIdS = taskFinishId.split(",");
        int taskFinishIdSLen = taskFinishIdS.length;
        List<SysTaskFinish> list = new ArrayList<>();
        for (int i = 0; i < taskFinishIdSLen; i++) {
            SysTaskFinish sysTaskFinish = new SysTaskFinish();
            sysTaskFinish.setId(Long.parseLong(taskFinishIdS[i]));
            list.add(sysTaskFinish);
        }
        sysTaskFinishRepository.delete(list);
    }
}
