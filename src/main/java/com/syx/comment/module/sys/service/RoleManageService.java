package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 * 角色管理的API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:18
 */
public interface RoleManageService {
    public JSONArray getAllRole();

    public JSONObject deleteRole(String id);

    public JSONArray getSingleRole(String id);

    public JSONObject changeRole(String roleId, String menuId, String menuPid, String menuPurview);

    public JSONObject insertRole(String roleName);

    public JSONObject updateRoleName(String roleId, String roleName);

    public JSONArray getUserRole(String roleId);
}
