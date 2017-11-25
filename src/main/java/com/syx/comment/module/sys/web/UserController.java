package com.syx.comment.module.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysArea;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.impl.UserManageServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 * 用户的控制层
 *
 * @author Msater Zg
 * @create 2017-11-07 19:24
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserManageServiceImpl userManageService;

    @ApiOperation(value = "judgeUser", notes = "登陆接口")
    @PostMapping(value = "/judgeUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户账号", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "userPwd", value = "用户密码", required = true, dataType = "STRING")
    })
    public ResponseEntity judgeUser(@RequestParam("userName") String userName,
                                    @RequestParam("userPwd") String userPwd) {
        try {
            JSONObject jsonObject = userManageService.judgeUser(userName, userPwd);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "getUserInformation", notes = "得到用户的个人信息")
    @GetMapping(value = "/getUserInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户账号", required = true, dataType = "STRING")
    })
    public ResponseEntity getUserInformation(@RequestParam("userName") String userName) {
        try {
            JSONObject jsonObject = userManageService.getUserInformation(userName);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "saveUserInformation", notes = "新建用户信息")
    @PutMapping(value = "/saveUserInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo", value = "用户信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveUserInformation(@RequestParam("userInfo") String userInfo) {
        try {
            SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
            sysUser = userManageService.saveUserInformation(sysUser);
            return ResponseEntity.ok().body(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "deleteUserInformation", notes = "删除用户信息")
    @PostMapping(value = "/deleteUserInformation")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户信息", required = true, dataType = "STRING")
    })
    public ResponseEntity deleteUserInformation(@RequestParam("userId") String userId) {
        try {
            JSONObject jsonObject = userManageService.deleteUserInformation(userId);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "getUserInformationByDep", notes = "得到用户的个人信息")
    @GetMapping(value = "/getUserInformationByDep")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depNo", value = "任务状态", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageSize", value = "第几页", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "pageNumber", value = "每页数量", required = true, dataType = "STRING")
    })
    public ResponseEntity getUserInformationByDep(@RequestParam("depNo") String depNo,
                                                  @RequestParam("pageSize") String pageSize,
                                                  @RequestParam("pageNumber") String pageNumber) {
        try {
            JSONObject jsonObject = userManageService.getUserInformationByDep(depNo, pageSize, pageNumber);
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
