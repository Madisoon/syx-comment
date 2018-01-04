package com.syx.comment.repository;

import com.syx.comment.entity.SysFinishTab;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 已读标记的repository
 *
 * @author Msater Zg
 * @create 2017-12-29 16:59
 */
public interface SysFinishTabRepository extends JpaRepository<SysFinishTab, Long> {
}
