package com.syx.comment.repository;

import com.syx.comment.entity.SysTaskConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 任务配置的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:56
 */
public interface SysTaskConfigRepository extends JpaRepository<SysTaskConfig, Long> {
}
