package com.syx.comment.module.sys.web;

import com.alibaba.fastjson.JSON;
import com.syx.comment.entity.SysArea;
import com.syx.comment.entity.SysDepartment;
import com.syx.comment.module.sys.service.impl.AreaManageServiceImpl;
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
 * 地区的控制层
 *
 * @author Msater Zg
 * @create 2017-11-07 19:22
 */
@RestController
@RequestMapping(value = "/area")
public class AreaController {
    @Autowired
    AreaManageServiceImpl areaManageService;

    @PostMapping(value = "/deleteAreaInformation")
    @ApiOperation(value = "deleteAreaInformation", notes = "删除地区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "地区id", required = true, dataType = "STRING")
    })
    public ResponseEntity getDepartmentInformation(@RequestParam("areaId") String areaId) {
        try {
            List<SysArea> list = areaManageService.deleteAreaInformation(areaId);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @PutMapping(value = "/saveAreaInformation")
    @ApiOperation(value = "saveAreaInformation", notes = "新增地区的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaInfo", value = "地区信息", required = true, dataType = "STRING")
    })
    public ResponseEntity saveAreaInformation(@RequestParam("areaInfo") String areaInfo) {
        try {
            SysArea sysArea = JSON.parseObject(areaInfo, SysArea.class);
            sysArea = areaManageService.saveAreaInformation(sysArea);
            return ResponseEntity.ok().body(sysArea);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getAllAreaInformation")
    @ApiOperation(value = "getAllAreaInformation", notes = "得到所有地区的信息")
    public ResponseEntity getAllAreaInformation() {
        try {
            List<SysArea> list = areaManageService.getAllAreaInformation();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
