package com.syx.comment.repository;

import com.syx.comment.entity.SysTaskConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述:
 * 任务配置的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:56
 */
public interface SysTaskConfigRepository extends JpaRepository<SysTaskConfig, Long> {
    /**
     * 根据系统编号查找配置
     *
     * @param packetNo
     * @return
     */
    List<SysTaskConfig> findSysTaskConfigByPacketNoOrderByGmtCreateDesc(Long packetNo);
}
