package com.syx.comment.module.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fantasi.common.db.dao.BaseDao;
import com.syx.comment.config.JwtConfig;
import com.syx.comment.entity.SysPacket;
import com.syx.comment.entity.SysRoleUser;
import com.syx.comment.entity.SysUser;
import com.syx.comment.module.sys.service.UserManageService;
import com.syx.comment.repository.SysPacketRepository;
import com.syx.comment.repository.SysRoleUserRepository;
import com.syx.comment.repository.SysUserRepository;
import com.syx.comment.utils.SqlEasy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    /**
     * 新建用户的类型,1代表是普通用户,0代表是审核人员
     */
    public final String ROLE_TYPE = "1";

    @Autowired
    SysUserRepository sysUserRepository;

    @Autowired
    SysPacketRepository sysPacketRepository;

    @Autowired
    JwtConfig jwtConfig;

    @Autowired
    SysRoleUserRepository sysRoleUserRepository;

    @Autowired
    BaseDao baseDao;

    @Override
    public JSONObject judgeUser(String userName, String userPassword) {
        SysUser sysUser = sysUserRepository.findSysUserByUserAccount(userName);
        JSONObject jsonObject = new JSONObject();
        if (sysUser == null) {
            jsonObject.put("result", 0);
        } else {
            SysPacket sysPacket = sysPacketRepository.findSysPacketByPacketNo(sysUser.getPacketNo().toString());
            Boolean flag = true;
            if (sysPacket != null) {
                flag = sysPacket.getPacketEndTime().getTime() >= System.currentTimeMillis();
            }
            if (flag) {
                if (userPassword.equals(sysUser.getUserPassword())) {
                    jsonObject.put("result", 1);
                    String sql = "SELECT a.user_account AS userAccount, a.user_token AS userToken, a.user_name AS userNickName, a.user_phone AS userPhone, " +
                            "a.user_dep AS userDep, a.packet_no AS packetNo,b.id AS roleId , a.id ,b.role_name " +
                            "AS roleName FROM sys_user a ,sys_role b ,sys_role_user c  " +
                            "WHERE a.user_account = c.user_account AND b.id = c.role_id " +
                            "AND a.user_account = ? ";
                    JSONObject jsonObjectUser = (JSONObject) JSON.toJSON(baseDao.rawQueryForMap(sql, new String[]{userName}));
                    String token = jwtConfig.createJWT(jsonObjectUser, "syx", "sys-comment", 15000000, "19950108wa!");
                    sysUser.setUserToken(token);
                    sysUserRepository.save(sysUser);
                    jsonObject.put("user", jsonObjectUser);
                } else {
                    jsonObject.put("result", 0);
                }
            } else {
                jsonObject.put("result", 0);
            }
        }
        return jsonObject;
    }

    @Override
    public JSONObject getUserInformation(String userAccount) {
        JSONObject returnJson = new JSONObject();
        List<String> listModule = new ArrayList<>();
        listModule.add("SELECT f.id,f.menu_pid,f.menu_name,f.menu_content,f.menu_attr ");
        listModule.add("FROM sys_user a,sys_role_user b,sys_role c,sys_role_menu d,sys_menu f  ");
        listModule.add("WHERE a.user_account = '" + userAccount + "'  ");
        listModule.add("AND a.user_account = b.user_account AND b.role_id = c.id  ");
        listModule.add("AND b.role_id = d.role_id AND d.menu_id = f.id  ");
        listModule.add("AND f.menu_pid = 0 ORDER BY f.menu_sort ");
        List<String> listFunction = new ArrayList<>();
        listFunction.add("SELECT a.*,b.menu_name AS menu_parent_name,b.menu_attr AS menu_parent_attr FROM (SELECT f.id,f.menu_pid,f.menu_name,f.menu_content,f.menu_attr ");
        listFunction.add("FROM sys_user a,sys_role_user b,sys_role c,sys_role_menu d,sys_menu f  ");
        listFunction.add(" WHERE a.user_account = '" + userAccount + "'  ");
        listFunction.add(" AND a.user_account = b.user_account AND b.role_id = c.id  ");
        listFunction.add("AND b.role_id = d.role_id AND d.menu_id = f.id  ");
        listFunction.add("AND f.menu_pid <> 0) a LEFT JOIN sys_menu b ON a.menu_pid = b.id ");
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
            String menu_id = jsModule.getString("id");
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

    @Override
    public SysUser saveUserInformation(SysUser sysUser, String roleType) {
        sysUser.setGmtCreate(new Date());
        SysRoleUser sysRoleUser = sysRoleUserRepository.findSysRoleUserByUserAccount(sysUser.getUserName());
        if (sysRoleUser == null) {
            sysRoleUser = new SysRoleUser();
            if (ROLE_TYPE.equals(roleType)) {
                sysRoleUser.setRoleId(Long.parseLong("4"));
            } else {
                sysRoleUser.setRoleId(Long.parseLong("5"));
            }
            sysRoleUser.setUserAccount(sysUser.getUserName());
            sysRoleUserRepository.save(sysRoleUser);
        }
        return sysUserRepository.save(sysUser);
    }

    @Override
    public JSONObject deleteUserInformation(String userId) {
        String[] userIdS = userId.split(",");
        int userIdSLen = userIdS.length;
        List<SysUser> list = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < userIdSLen; i++) {
            SysUser sysUser = new SysUser();
            sysUser.setId(Long.parseLong(userIdS[i]));
            sysUser.setUserName("");
            list.add(sysUser);
            SysUser sysUserData = sysUserRepository.findOne(Long.parseLong(userIdS[i]));
            String userName = sysUserData.getUserName();
            String roleDelete = "DELETE FROM sys_role_user WHERE user_id = ? ";
            baseDao.execute(roleDelete, new String[]{userName});
            String finishDelete = "DELETE FROM sys_task_finish WHERE task_creater = ? ";
            baseDao.execute(roleDelete, new String[]{finishDelete});
        }
        try {
            // 删除角色
            sysUserRepository.delete(list);
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public JSONObject getUserInformationByDep(String roleType, String sysPacketNo, String depNo, String pageSize, String pageNumber) {
        JSONObject jsonObject = new JSONObject();
        if (ROLE_TYPE.equals(roleType)) {
            String sqlTotal = "SELECT * FROM sys_user a WHERE a.user_dep = ? AND a.packet_no = ? ORDER BY a.gmt_create DESC ";
            String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
            List<Map<String, String>> list = baseDao.rawQuery(sqlTotal, new String[]{depNo, sysPacketNo});
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{depNo, sysPacketNo}));
            jsonObject.put("total", list.size());
            jsonObject.put("data", jsonArray);
        } else {
            String sqlTotal = "SELECT a.* FROM sys_user a, sys_role_user b  " +
                    "WHERE a.packet_no = ?   " +
                    "AND a.user_account = b.user_account AND b.role_id = '5' " +
                    "ORDER BY a.gmt_create DESC ";
            String sqlPage = sqlTotal + SqlEasy.limitPage(pageSize, pageNumber);
            List<Map<String, String>> list = baseDao.rawQuery(sqlTotal, new String[]{sysPacketNo});
            JSONArray jsonArray = (JSONArray) JSON.toJSON(baseDao.rawQuery(sqlPage, new String[]{sysPacketNo}));
            jsonObject.put("total", list.size());
            jsonObject.put("data", jsonArray);
        }
        return jsonObject;
    }
}
