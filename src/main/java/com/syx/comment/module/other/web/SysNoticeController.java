package com.syx.comment.module.other.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysNotice;
import com.syx.comment.module.other.service.SysNoticeService;
import com.syx.comment.module.sys.web.ExecResult;
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
 * 管理通知的控制层
 *
 * @author Msater Zg
 * @create 2018-02-02 19:28
 */
@RestController
@RequestMapping(value = "/notice")
@Api(value = "SysNoticeController", description = "管理通知的API")
public class SysNoticeController {
    @Autowired
    SysNoticeService sysNoticeService;

    @PutMapping(value = "/saveNotice")
    @ApiOperation(value = "saveNotice", notes = "插入通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeData", value = "通知的信息", required = true, dataType = "STRING")
    })
    public ResponseEntity getPacketInformation(@RequestParam("noticeData") String noticeData) {
        try {
            SysNotice sysNotice = JSON.parseObject(noticeData, SysNotice.class);
            sysNotice = sysNoticeService.saveNotice(sysNotice);
            return ResponseEntity.ok().body(sysNotice);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/listNoticeInformation")
    @ApiOperation(value = "listNoticeInformation", notes = "获取通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchData", value = "搜索条件", required = true, dataType = "STRING")
    })
    public ResponseEntity listNoticeInformation(@RequestParam("searchData") String searchData) {
        try {
            List<SysNotice> sysNotice = sysNoticeService.listNoticeInformation(searchData);
            return ResponseEntity.ok().body(sysNotice);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteNotice")
    @ApiOperation(value = "deleteNotice", notes = "删除通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "通知的id", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteNotice(@RequestParam("id") String id) {
        try {
            JSONObject jsonObject = sysNoticeService.deleteNotice(Long.parseLong(id));
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();

            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/listNoticeInformationByUser")
    @ApiOperation(value = "listNoticeInformationByUser", notes = "删除通知")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userAccount", value = "用户名", required = true, dataType = "STRING")
    })
    public ResponseEntity listNoticeInformationByUser(@RequestParam("userAccount") String userAccount) {
        try {
            List<SysNotice> list = sysNoticeService.listNoticeInformationByUser(userAccount);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();

            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
