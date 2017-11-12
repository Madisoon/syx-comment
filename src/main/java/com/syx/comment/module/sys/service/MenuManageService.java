package com.syx.comment.module.sys.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 * 菜单管理的API
 *
 * @author Msater Zg
 * @create 2017-11-07 19:17
 */
public interface MenuManageService {
    public JSONObject insertModule(String moduleValue, String moduleUrl, String moduleId);

    public JSONArray getAllModule();

    public JSONObject deleteModule(String moduleId);

    public JSONArray getAllSecondModule(String moduleId);

    public JSONObject updateModuleInfo(String menuId, String menuName, String menuContent);
}
