package com.syx.comment.module.task.web;

import com.alibaba.fastjson.JSON;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.web.ExecResult;
import com.syx.comment.module.task.service.TaskConfigService;
import com.syx.comment.module.task.service.impl.TaskConfigServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 任务配置的服务层
 *
 * @author Msater Zg
 * @create 2017-11-13 19:30
 */
@RestController
@RequestMapping(value = "/taskConfig")
@Api(value = "TaskConfigController", description = "管理任务配置的API")
public class TaskConfigController {

    @Autowired
    TaskConfigServiceImpl taskConfigService;

    @PutMapping(value = "/saveTaskConfigInformation")
    @ApiOperation(value = "saveTaskConfigInformation", notes = "更新数据包信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskConfig", value = "任务配置的信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveTaskConfigInformation(@RequestParam("taskConfig") String taskConfig) {
        try {
            SysTaskConfig sysTaskConfig = JSON.parseObject(taskConfig, SysTaskConfig.class);
            sysTaskConfig = taskConfigService.saveTaskConfigInformation(sysTaskConfig);
            return ResponseEntity.ok().body(sysTaskConfig);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
