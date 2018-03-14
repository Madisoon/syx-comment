package com.syx.comment.module.other.web;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysGuide;
import com.syx.comment.module.other.service.SysLogService;
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
 * 日志的控制层
 *
 * @author Msater Zg
 * @create 2018-03-05 19:12
 */

@RestController
@RequestMapping(value = "/log")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @GetMapping(value = "/listSysLog")
    @ApiOperation(value = "listSysLog", notes = "获取排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "每一页的数量", required = true, dataType = "STRING")
    })
    public ResponseEntity listSysLog(@RequestParam("pageNumber") String pageNumber,
                                     @RequestParam("pageSize") String pageSize) {
        try {
            JSONObject jsonObject = sysLogService.listSysLog(pageNumber, pageSize);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/userIsGuide")
    @ApiOperation(value = "userIsGuide", notes = "获取用户的指导页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号名", required = true, dataType = "STRING"),
    })
    public ResponseEntity userIsGuide(@RequestParam("userAccount") String userAccount) {
        try {
            SysGuide sysGuide = sysLogService.userIsGuide(userAccount);
            JSONObject jsonObject = new JSONObject();
            if (sysGuide != null) {
                jsonObject.put("result", 1);
            } else {
                jsonObject.put("result", 0);
            }
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/saveUserAccountGuide")
    @ApiOperation(value = "saveUserAccountGuide", notes = "插入指导页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户账号名", required = true, dataType = "STRING"),
    })
    public ResponseEntity saveUserAccountGuide(@RequestParam("userAccount") String userAccount) {
        try {
            SysGuide sysGuide = sysLogService.saveUserAccountGuide(userAccount);
            return ResponseEntity.ok().body(sysGuide);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
