package com.syx.comment.repository;

import com.syx.comment.entity.SysGuide;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 系统指导页面的repository
 *
 * @author Msater Zg
 * @create 2018-03-13 19:58
 */
public interface SysGuideRepository extends JpaRepository<SysGuide, Long> {
    SysGuide findSysGuideByUserAccount(String userAccount);
}
