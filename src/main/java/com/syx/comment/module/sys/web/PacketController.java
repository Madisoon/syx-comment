package com.syx.comment.module.sys.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.impl.PacketManageServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 * 数据的控制层
 *
 * @author Msater Zg
 * @create 2017-11-12 11:22
 */

@RestController
@RequestMapping(value = "/packet")
public class PacketController {
    @Autowired
    PacketManageServiceImpl packetManageService;

    @PutMapping(value = "/savePacketInformation")
    @ApiOperation(value = "savePacketInformation", notes = "更新数据包信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "packetInfo", value = "数据包信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "userInfo", value = "数据包管理员信息", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "areaId", value = "地区id", required = true, dataType = "STRING")
    })
    public ResponseEntity savePacketInformation(@RequestParam("packetInfo") String packetInfo,
                                                @RequestParam("areaId") String areaId,
                                                @RequestParam("userInfo") String userInfo) {
        try {
            SysPacket sysPacket = JSON.parseObject(packetInfo, SysPacket.class);
            SysUser sysUser = JSON.parseObject(userInfo, SysUser.class);
            sysPacket = packetManageService.savePacketInformation(sysPacket, sysUser, areaId);
            return ResponseEntity.ok().body(sysPacket);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value = "/getPacketInformation")
    @ApiOperation(value = "getPacketInformation", notes = "更新数据包信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "数据包信息", required = true, dataType = "STRING")
    })
    public ResponseEntity getPacketInformation(@RequestParam("areaId") String areaId) {
        try {
            JSONArray jsonArray = packetManageService.getPacketInformation(areaId);
            return ResponseEntity.ok().body(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er = new ExecResult(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
