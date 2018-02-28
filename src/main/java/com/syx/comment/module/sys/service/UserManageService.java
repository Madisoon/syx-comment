package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysUser;

/**
 * 描述:
 * 系统用户管理——API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:14
 */
public interface UserManageService {
    /**
     * 登陆判断
     *
     * @param userName
     * @param userPassword
     * @return
     */
    JSONObject judgeUser(String userName, String userPassword);

    /**
     * 根据账号得到用户的个人信息，包括权限账号等信息
     *
     * @param userName
     * @return
     */
    JSONObject getUserInformation(String userName);

    /**
     * 保存用户信息
     *
     * @param sysUser
     * @param roleType
     * @return SysUser
     */
    SysUser saveUserInformation(SysUser sysUser, String roleType);

    /**
     * 根据id删除用户
     *
     * @param userId
     * @return JSONObject
     */
    JSONObject deleteUserInformation(String userId);

    /**
     * 根据用户获取的他的身份特征信息
     * @param userAccount
     * @return
     */
    JSONObject getPersonInformationByUserAccount(String userAccount);

    /**
     * 根据角色和部门获取用户
     * @param roleType
     * @param sysPacketNo
     * @param depNo
     * @param pageSize
     * @param pageNumber
     * @return JSONObject
     */
    JSONObject getUserInformationByDep(String roleType, String sysPacketNo, String depNo, String pageSize, String pageNumber);
}
