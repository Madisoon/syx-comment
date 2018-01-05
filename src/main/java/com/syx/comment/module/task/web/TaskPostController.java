package com.syx.comment.module.task.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysTaskConfig;
import com.syx.comment.entity.SysTaskFinish;
import com.syx.comment.module.sys.web.ExecResult;
import com.syx.comment.module.task.service.TaskPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述:
 * 任务发布的控制层
 *
 * @author Msater Zg
 * @create 2017-11-23 17:40
 */

@RestController
@RequestMapping(value = "/taskPost")
@Api(value = "TaskPostController", description = "管理任务发布的API")
public class TaskPostController {
    @Autowired
    TaskPostService taskPostService;

    @PutMapping(value = "/saveTaskFinishInformation")
    @ApiOperation(value = "saveTaskFinishInformation", notes = "发布任务配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskPostInfo", value = "任务发布的信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveTaskFinishInformation(@RequestParam("taskPostInfo") String taskPostInfo) {
        try {
            SysTaskFinish sysTaskFinish = JSON.parseObject(taskPostInfo, SysTaskFinish.class);
            sysTaskFinish = taskPostService.saveTaskFinishInformation(sysTaskFinish);
            return ResponseEntity.ok().body(sysTaskFinish);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteTaskFinishInformation")
    @ApiOperation(value = "deleteTaskFinishInformation", notes = "删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "需要删除的id", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteTaskFinishInformation(@RequestParam("taskId") String taskId) {
        try {
            JSONObject jsonObject = taskPostService.deleteTaskFinishInformation(taskId);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepTaskInformation")
    @ApiOperation(value = "getDepTaskInformation", notes = "发布任务配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depNo", value = "部门编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "searchData", value = "部门编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageNumber", value = "每页数量", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepTaskInformation(@RequestParam("depNo") String depNo,
                                                @RequestParam("searchData") String searchData,
                                                @RequestParam("pageSize") String pageSize,
                                                @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = taskPostService.getDepTaskInformation(depNo, searchData, pageSize, pageNumber);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getPacketTaskInformation")
    @ApiOperation(value = "getPacketTaskInformation", notes = "发布任务配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPacketNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskStatus", value = "任务状态", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "searchData", value = "筛选条件", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageNumber", value = "每页数量", required = true, dataType = "STRING")
    })
    public ResponseEntity getPacketTaskInformation(@RequestParam("sysPacketNo") String sysPacketNo,
                                                   @RequestParam("taskStatus") String taskStatus,
                                                   @RequestParam("searchData") String searchData,
                                                   @RequestParam("pageSize") String pageSize,
                                                   @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = new JSONObject();
            if ("{}".equals(searchData)) {
                jsonObject = taskPostService.getPacketInformation(sysPacketNo, taskStatus, pageSize, pageNumber);
            } else {
                jsonObject = taskPostService.getTaskChooseInformation(sysPacketNo, searchData, pageSize, pageNumber);
            }
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/saveTaskFinishInformationPart")
    @ApiOperation(value = "saveTaskFinishInformationPart", notes = "局部修改任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskPostInfo", value = "任务信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "userName", value = "审核人账号", required = true, dataType = "STRING")
    })
    public ResponseEntity saveTaskFinishInformationPart(@RequestParam("taskPostInfo") String taskPostInfo,
                                                        @RequestParam("userName") String userName) {
        try {
            SysTaskFinish sysTaskFinish = JSON.parseObject(taskPostInfo, SysTaskFinish.class);
            sysTaskFinish = taskPostService.saveTaskFinishInformationPart(sysTaskFinish, userName);
            return ResponseEntity.ok().body(sysTaskFinish);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/uploadImageFile")
    public String uploadImageFile(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String str = sdf.format(date);
            String filePath = request.getSession().getServletContext().getRealPath("/") + str + ""
                    + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return str + file.getOriginalFilename();
        }
        return "";
    }

    @GetMapping(value = "/getTaskRankInformation")
    @ApiOperation(value = "getTaskRankInformation", notes = "获取排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPacketNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "rankType", value = "排名的类型", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "searchData", value = "搜索的排序", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageNumber", value = "每页数量", required = true, dataType = "STRING")
    })
    public ResponseEntity getTaskRankInformation(@RequestParam("sysPacketNo") String sysPacketNo,
                                                 @RequestParam("rankType") String rankType,
                                                 @RequestParam("searchData") String searchData,
                                                 @RequestParam("pageSize") String pageSize,
                                                 @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = taskPostService.getTaskRankInformation(sysPacketNo, rankType, searchData, pageSize, pageNumber);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/exportExcelTaskRank")
    @ApiOperation(value = "exportExcelTaskRank", notes = "获取排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysPacketNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "rankType", value = "排名的类型", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "searchData", value = "搜索的排序", required = true, dataType = "STRING")
    })
    public ResponseEntity exportExcelTaskRank(@RequestParam("sysPacketNo") String sysPacketNo,
                                              @RequestParam("rankType") String rankType,
                                              @RequestParam("searchData") String searchData, HttpServletRequest request) {
        try {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            String fileUrl = taskPostService.exportExcelTaskRank(sysPacketNo, rankType, searchData, filePath);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", fileUrl);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
