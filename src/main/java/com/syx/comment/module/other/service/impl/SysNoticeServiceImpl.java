package com.syx.comment.module.other.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.syx.comment.entity.SysNotice;
import com.syx.comment.module.other.service.SysNoticeService;
import com.syx.comment.repository.SysNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 任务通知的接口实现
 *
 * @author Msater Zg
 * @create 2018-02-02 19:28
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    SysNoticeRepository sysNoticeRepository;

    @Override
    public SysNotice saveNotice(SysNotice sysNotice) {
        sysNotice.setGmtModified(new Date());
        sysNotice.setGmtCreate(new Date());
        return sysNoticeRepository.save(sysNotice);
    }

    @Override
    public JSONObject deleteNotice(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            sysNoticeRepository.delete(id);
            jsonObject.put("result", 1);
        } catch (Exception e) {
            jsonObject.put("result", 0);
        }
        return jsonObject;
    }

    @Override
    public List<SysNotice> listNoticeInformation(String searchData) {
        List<SysNotice> list = new ArrayList<>();
        if (!"".equals(searchData)) {
            list = sysNoticeRepository.findSysNoticeByNoticeType(0);
        }
        return list;
    }

    @Override
    public List<SysNotice> listNoticeInformationByUser(String userAccount) {
        return sysNoticeRepository.findSysNoticeByNoticeReceiveOrNoticeTypeOrderByGmtCreateDesc(userAccount, 0);
    }
}
