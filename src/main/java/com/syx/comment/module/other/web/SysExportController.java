package com.syx.comment.module.other.web;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.module.other.service.SysExportService;
import com.syx.comment.module.sys.web.ExecResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 描述:
 * 系统相关的导出控制类
 *
 * @author Msater Zg
 * @create 2018-02-26 20:16
 */
@RestController
@RequestMapping(value = "/export")
public class SysExportController {

    @Autowired
    SysExportService sysExportService;

    @GetMapping(value = "/exportDepOrTypeRank")
    @ApiOperation(value = "exportDepOrTypeRank", notes = "获取排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "startTime", value = "排名的类型", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "endTime", value = "搜索的排序", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "type", value = "搜索的排序", required = true, dataType = "STRING")
    })
    public ResponseEntity exportDepOrTypeRank(@RequestParam("packetNo") String packetNo,
                                              @RequestParam("startTime") String startTime,
                                              @RequestParam("endTime") String endTime, @RequestParam("type") String type,
                                              HttpServletRequest request) {
        try {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            String fileUrl = sysExportService.rankDataExport(packetNo, startTime, endTime, type, filePath);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", fileUrl);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/taskDataExport")
    @ApiOperation(value = "taskDataExport", notes = "获取排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "searchData", value = "搜索条件", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskType", value = "任务类型", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "taskStatus", value = "任务状态", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "depNo", value = "系统编号", required = true, dataType = "STRING")
    })
    public ResponseEntity taskDataExport(@RequestParam("userAccount") String userAccount,
                                         @RequestParam("searchData") String searchData,
                                         @RequestParam("taskType") String taskType,
                                         @RequestParam("taskStatus") String taskStatus,
                                         @RequestParam("depNo") String depNo,
                                         HttpServletRequest request) {
        try {
            String filePath = request.getSession().getServletContext().getRealPath("/");
            String fileUrl = sysExportService.taskDataExport(userAccount, searchData, taskType,
                    taskStatus, depNo, filePath);
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
