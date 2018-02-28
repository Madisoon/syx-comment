package com.syx.comment.module.other.service;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysNotice;

import java.util.List;

/**
 * 描述:
 * 系统通知的API
 *
 * @author Msater Zg
 * @create 2018-02-02 19:28
 */
public interface SysNoticeService {
    /**
     * 添加通知
     *
     * @param sysNotice
     * @return
     */
    SysNotice saveNotice(SysNotice sysNotice);

    /**
     * 删除通知
     *
     * @param id
     * @return
     */
    JSONObject deleteNotice(Long id);

    /**
     * 获取系统通知
     *
     * @param searchData
     * @return
     */
    List<SysNotice> listNoticeInformation(String searchData);

    List<SysNotice> listNoticeInformationByUser(String userAccount);
}
