package com.syx.comment.module.other.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.module.other.service.impl.SysRankServiceImpl;
import com.syx.comment.module.sys.web.ExecResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 * 统计的控制类
 *
 * @author Msater Zg
 * @create 2018-02-13 13:04
 */
@RestController
@RequestMapping(value = "/rank")
@Api(value = "SysRankController", description = "排名的API")
public class SysRankController {
    @Autowired
    SysRankServiceImpl sysRankService;

    @GetMapping(value = "/getDepMarkRankData")
    @ApiOperation(value = "getDepMarkRankData", notes = "插入通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepMarkRankData(@RequestParam("packetNo") String packetNo,
                                             @RequestParam("startTime") String startTime,
                                             @RequestParam("endTime") String endTime) {
        try {
            JSONArray jsonArray = sysRankService.getDepMarkRankData(packetNo, startTime, endTime);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepCountRankData")
    @ApiOperation(value = "getDepCountRankData", notes = "插入通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepCountRankData(@RequestParam("packetNo") String packetNo,
                                              @RequestParam("startTime") String startTime,
                                              @RequestParam("endTime") String endTime) {
        try {
            JSONObject jsonObject = sysRankService.getDepCountRankData(packetNo, startTime, endTime);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepTypeRankData")
    @ApiOperation(value = "getDepTypeRankData", notes = "插入通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepTypeRankData(@RequestParam("packetNo") String packetNo,
                                             @RequestParam("startTime") String startTime,
                                             @RequestParam("endTime") String endTime) {
        try {
            JSONArray jsonArray = sysRankService.getDepTypeRankData(packetNo, startTime, endTime);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepNumberRankData")
    @ApiOperation(value = "getDepNumberRankData", notes = "插入通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetNo", value = "系统编号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepNumberRankData(@RequestParam("packetNo") String packetNo,
                                               @RequestParam("startTime") String startTime,
                                               @RequestParam("endTime") String endTime) {
        try {
            JSONArray jsonArray = sysRankService.getDepNumberRankData(packetNo, startTime, endTime);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
