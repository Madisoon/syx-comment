package com.syx.comment.repository;

import com.syx.comment.entity.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;


/**
 * 描述:
 * 系统日志的repository
 *
 * @author Msater Zg
 * @create 2018-03-05 18:40
 */
public interface SysLogRepository extends JpaRepository<SysLog, Long> {
    SysLog findSysLogByUserAccountAndGmtCreateLike(String userAccount, Date gmtModified);
}
