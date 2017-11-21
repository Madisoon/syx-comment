package com.syx.comment.module.sys.web;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysArea;
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
}
