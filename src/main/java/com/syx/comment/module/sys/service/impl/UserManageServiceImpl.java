package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.config.JwtConfig;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.UserManageService;
import com.syx.comment.repository.SysUserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 用户管理的接口实现
 *
 * @author Msater Zg
 * @create 2017-11-07 19:16
 */

@Service
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    BaseDao baseDao;

    @Override
    public JSONObject judgeUser(String userName, String userPassword) {
        SysUser sysUser = sysUserRepository.findSysUserByUserName(userName);
        JSONObject jsonObject = new JSONObject();
        if (sysUser == null) {
            jsonObject.put("result", 0);
        } else {
            if (userPassword.equals(sysUser.getUserPwd())) {
                jsonObject.put("result", 1);
                jsonObject.put("user", sysUser);
            } else {
                jsonObject.put("result", 0);
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject getUserInformation(String userName) {
        JSONObject returnJson = new JSONObject();
        List<String> listModule = new ArrayList<>();
        listModule.add("SELECT f.menu_id,f.menu_pid,f.menu_name,f.menu_content,f.menu_attr ");
        listModule.add("FROM sys_user a,sys_role_user b,sys_role c,sys_role_menu d,sys_menu f  ");
        listModule.add("WHERE a.user_name = '" + userName + "'  ");
        listModule.add("AND a.user_name = b.user_id AND b.role_id = c.id  ");
        listModule.add("AND b.role_id = d.role_id AND d.menu_id = f.menu_id  ");
        listModule.add("AND f.menu_pid = 0 ORDER BY f.menu_sort ");
        List<String> listFunction = new ArrayList<>();
        listFunction.add("SELECT a.*,b.menu_name AS menu_parent_name,b.menu_attr AS menu_parent_attr FROM (SELECT f.menu_id,f.menu_pid,f.menu_name,f.menu_content,f.menu_attr ");
        listFunction.add("FROM sys_user a,sys_role_user b,sys_role c,sys_role_menu d,sys_menu f  ");
        listFunction.add(" WHERE a.user_name = '" + userName + "'  ");
        listFunction.add(" AND a.user_name = b.user_id AND b.role_id = c.id  ");
        listFunction.add("AND b.role_id = d.role_id AND d.menu_id = f.menu_id  ");
        listFunction.add("AND f.menu_pid <> 0) a LEFT JOIN sys_menu b ON a.menu_pid = b.menu_id ");
        String listModuleString = StringUtils.join(listModule, "");
        String listFunctionString = StringUtils.join(listFunction, "");
        List<Map<String, String>> execResultModule = baseDao.rawQuery(listModuleString);
        List<Map<String, String>> execResultFunction = baseDao.rawQuery(listFunctionString);
        JSONArray jsonArrayModule = (JSONArray) JSON.toJSON(execResultModule);
        JSONArray jsonArrayFunction = (JSONArray) JSON.toJSON(execResultFunction);
        JSONArray jsonObjectFunction = new JSONArray();
        for (int i = 0, len = jsonArrayModule.size(); i < len; i++) {
            JSONObject jsModule = jsonArrayModule.getJSONObject(i);
            JSONArray arryObject = new JSONArray();
            String menu_id = jsModule.getString("menu_id");
            for (int j = 0, lenF = jsonArrayFunction.size(); j < lenF; j++) {
                JSONObject jsFunction = jsonArrayFunction.getJSONObject(j);
                if (menu_id.equals(jsFunction.getString("menu_pid"))) {
                    arryObject.add(jsFunction);
                }
            }
            jsonObjectFunction.add(arryObject);

        }
        returnJson.put("module", jsonArrayModule);
        returnJson.put("function", jsonObjectFunction);
        return returnJson;
    }
}
