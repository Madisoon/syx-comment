package com.syx.comment.repository;

import com.syx.comment.entity.SysStarTab;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 标星功能的repository
 *
 * @author Msater Zg
 * @create 2018-03-03 19:31
 */
public interface SysStarTabRepository extends JpaRepository<SysStarTab, Long> {
}
