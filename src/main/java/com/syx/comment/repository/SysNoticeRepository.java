package com.syx.comment.repository;

import com.syx.comment.entity.SysNotice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 通知的repository
 *
 * @author Msater Zg
 * @create 2018-02-12 17:06
 */
public interface SysNoticeRepository extends JpaRepository<SysNotice, Long> {
    /**
     * 根据类型获取系统通知
     *
     * @param type
     * @return
     */
    List<SysNotice> findSysNoticeByNoticeType(int type);

    /**
     * 获取个人的相关通知
     *
     * @param userAccount
     * @param noticeType
     * @return
     */
    List<SysNotice> findSysNoticeByNoticeReceiveOrNoticeTypeOrderByGmtCreateDesc(String userAccount, int noticeType);
}
