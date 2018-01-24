package com.syx.comment.module.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.DepartmentManageService;
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
 * 部门的控制层
 *
 * @author Msater Zg
 * @create 2017-11-07 19:22
 */

@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Autowired
    DepartmentManageService departmentManageService;

    @PutMapping(value = "/saveDepartmentInformation")
    @ApiOperation(value = "saveDepartmentInformation", notes = "添加部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depInfo", value = "部门信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "userInfo", value = "部长信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveDepartmentInformation(@RequestParam("depInfo") String depInfo,
                                                    @RequestParam("userInfo") String userInfo) {
        try {
            SysDepartment sysDepartment = JSON.parseObject(depInfo, SysDepartment.class);
            System.out.println(sysDepartment.getDepNo());
            SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
            sysDepartment = departmentManageService.saveDepartmentInformation(sysDepartment, sysUser);
            return ResponseEntity.ok().body(sysDepartment);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepartmentInformation")
    @ApiOperation(value = "getDepartmentInformation", notes = "查找部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depPacketNo", value = "数据包码", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepartmentInformation(@RequestParam("depPacketNo") String depPacketNo) {
        try {
            JSONArray jsonArray = departmentManageService.getDepartmentInformation(depPacketNo);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PostMapping(value = "/deleteDepartment")
    @ApiOperation(value = "deleteDepartment", notes = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depId", value = "部门id", required = true, dataType = "String")
    })
    public ResponseEntity deleteDepartment(@RequestParam("depId") String depId) {
        try {
            departmentManageService.deleteDepartment(Long.parseLong(depId));
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "1");
            return ResponseEntity.ok().body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getDepartmentByDepNo")
    @ApiOperation(value = "getDepartmentByDepNo", notes = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depNo", value = "部门depNo", required = true, dataType = "String")
    })
    public ResponseEntity getDepartmentByDepNo(@RequestParam("depNo") String depNo) {
        try {
            SysDepartment sysDepartment = departmentManageService.getDepartmentByDepNo(depNo);
            return ResponseEntity.ok().body(sysDepartment);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }


}
