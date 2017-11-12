package com.syx.comment.module.sys.web;

import com.syx.comment.module.sys.service.impl.RoleManageServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 角色的控制层
 *
 * @author Msater Zg
 * @create 2017-11-07 19:23
 */
@RestController
@RequestMapping(value = "/role")
@Api(value = "RoleController", description = "管理系统角色的API")
public class RoleController {
    @Autowired
    private RoleManageServiceImpl roleManageService;

    @ApiOperation(value = "获取所有的角色", notes = "无")
    @RequestMapping(value = "/getAllRole", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户ID", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "adminJudge", value = "用户是否为超级管理员", required = true, dataType = "STRING")
    })
    public String getAllDep() {
        String result = roleManageService.getAllRole().toString();
        return result;
    }

    @RequestMapping(value = "/getSingleRole", method = RequestMethod.GET)
    @ApiOperation(value = "getSingleRole", notes = "获取单个角色的所有的信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "STRING")
    })
    public String getSingleRole(@RequestParam("roleId") String roleId) {
        String result = roleManageService.getSingleRole(roleId).toString();
        return result;
    }

    @RequestMapping(value = "/changeRole", method = RequestMethod.POST)
    @ApiOperation(value = "changeRole", notes = "改变角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "menuId", value = "菜单id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "menuPid", value = "菜单父级id", required = true, dataType = "STRING"),
            @ApiImplicitParam(name = "menuPurview", value = "操作权限", required = true, dataType = "STRING")
    })
    public String changeRole(@RequestParam("roleId") String roleId,
                             @RequestParam("menuId") String menuId,
                             @RequestParam("menuPid") String menuPid,
                             @RequestParam("menuPurview") String menuPurview) {
        String result = roleManageService.changeRole(roleId, menuId, menuPid, menuPurview).toString();
        return result;
    }

    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    @ApiOperation(value = "deleteRole", notes = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "STRING")
    })
    public String deleteRole(@RequestParam("roleId") String roleId) {
        String result = roleManageService.deleteRole(roleId).toString();
        return result;
    }

    @RequestMapping(value = "/insertRole", method = RequestMethod.PUT)
    @ApiOperation(value = "insertRole", notes = "插入角色")
    public String insertRole(@RequestParam("roleName") String roleName) {
        String result = roleManageService.insertRole(roleName).toString();
        return result;
    }

    @RequestMapping(value = "/updateRoleName", method = RequestMethod.POST)
    @ApiOperation(value = "updateRoleName", notes = "修改角色")
    public String updateRoleName(@RequestParam("roleName") String roleName,
                                 @RequestParam("roleId") String roleId) {
        String result = roleManageService.updateRoleName(roleId, roleName).toString();
        return result;
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.GET)
    @ApiOperation(value = "getUserRole", notes = "获取角色相关的个人信息")
    public String getUserRole(@RequestParam("roleId") String roleId) {
        String result = roleManageService.getUserRole(roleId).toString();
        return result;
    }
}
