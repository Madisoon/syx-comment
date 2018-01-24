package com.syx.comment.module.task.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.entity.SysTaskRelease;
import com.syx.comment.entity.SysTaskReleaseDepartment;
import com.syx.comment.module.sys.web.ExecResult;
import com.syx.comment.module.task.service.impl.TaskConfigServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation(value = "saveTaskConfigInformation", notes = "发布任务配置")
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

    @GetMapping(value = "/getTaskConfigByPacketNo")
    @ApiOperation(value = "getTaskConfigByPacketNo", notes = "根据系统编号获取任务配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING")
    })
    public ResponseEntity getTaskConfigByPacketNo(@RequestParam("packetNo") String packetNo) {
        try {
            return ResponseEntity.ok().body(taskConfigService.getTaskConfigByPacketNo(packetNo));
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/saveTaskReleaseInformation")
    @ApiOperation(value = "saveTaskReleaseInformation", notes = "发布任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskInfo", value = "任务信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskDep", value = "部门的一些信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveTaskReleaseInformation(@RequestParam("taskInfo") String taskInfo,
                                                     @RequestParam("taskDep") String taskDep) {
        try {
            SysTaskRelease sysTaskRelease = JSON.parseObject(taskInfo, SysTaskRelease.class);
            sysTaskRelease = taskConfigService.saveTaskReleaseInformation(sysTaskRelease, taskDep);
            return ResponseEntity.ok().body(sysTaskRelease);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/saveTaskDepartmentInformation")
    @ApiOperation(value = "saveTaskDepartmentInformation", notes = "任务的部门修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务的id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskDep", value = "任务的适用部门", required = true, dataType = "STRING")
    })
    public ResponseEntity saveTaskDepartmentInformation(@RequestParam("taskId") String taskId,
                                                        @RequestParam("taskDepartment") String taskDep) {
        try {
            List<SysTaskReleaseDepartment> list = taskConfigService.saveTaskDepartmentInformation(taskId, taskDep);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getTaskReleaseInformation")
    @ApiOperation(value = "getTaskReleaseInformation", notes = "发布任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPacketNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "每一页的大小", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageNumber", value = "页数", required = true, dataType = "STRING")
    })
    public ResponseEntity getTaskReleaseInformation(@RequestParam("sysPacketNo") String sysPacketNo,
                                                    @RequestParam("pageSize") String pageSize,
                                                    @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = taskConfigService.getTaskReleaseInformation(sysPacketNo, pageNumber, pageSize);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteTaskConfigInformation")
    @ApiOperation(value = "deleteTaskConfigInformation", notes = "删除任务配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskConfigId", value = "任务配置的id", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteTaskConfigInformation(@RequestParam("taskConfigId") String taskConfigId) {
        try {
            JSONObject jsonObject = taskConfigService.deleteTaskConfigInformation(taskConfigId);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteTaskInformation")
    @ApiOperation(value = "deleteTaskInformation", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务的id", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteTaskInformation(@RequestParam("taskId") String taskConfigId) {
        try {
            JSONObject jsonObject = taskConfigService.deleteTaskInformation(taskConfigId);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getAllNoteInformation")
    @ApiOperation(value = "getAllNoteInformation", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPacketNo", value = "系统的数据包编码", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "depNo", value = "部门编码", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "userName", value = "用户登陆名称", required = true, dataType = "STRING")
    })
    public ResponseEntity getAllNoteInformation(@RequestParam("sysPacketNo") String sysPacketNo,
                                                @RequestParam("depNo") String depNo,
                                                @RequestParam("userName") String userName,
                                                @RequestParam("pageSize") String pageSize,
                                                @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = taskConfigService.getAllNoteInformation(sysPacketNo, depNo, userName, pageSize, pageNumber);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/tabReadOrFinish")
    @ApiOperation(value = "tabReadOrFinish", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "任务的id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskId", value = "任务的id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskId", value = "任务的id", required = true, dataType = "STRING")
    })
    public ResponseEntity tabReadOrFinish(@RequestParam("type") String type,
                                          @RequestParam("taskReleaseId") String taskReleaseId,
                                          @RequestParam("userName") String userName) {
        try {
            JSONObject jsonObject = taskConfigService.tabReadOrFinish(type, taskReleaseId, userName);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/removeReadOrFinish")
    @ApiOperation(value = "removeReadOrFinish", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "任务的id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskId", value = "任务的id", required = true, dataType = "STRING")
    })
    public ResponseEntity removeReadOrFinish(@RequestParam("type") String type,
                                             @RequestParam("id") String id) {
        try {
            JSONObject jsonObject = taskConfigService.removeReadOrFinish(type, id);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getTaskReleaseByNumber")
    @ApiOperation(value = "getTaskReleaseByNumber", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskNumber", value = "任务编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "depNo", value = "部门的编号", required = true, dataType = "STRING")
    })
    public ResponseEntity getTaskReleaseByNumber(@RequestParam("taskNumber") String taskNumber,
                                                 @RequestParam("depNo") String depNo) {
        try {
            JSONObject jsonObject = taskConfigService.getTaskReleaseByNumber(taskNumber, depNo);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
