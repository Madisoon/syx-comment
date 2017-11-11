package com.syx.comment.repository;

import com.syx.comment.entity.SysPacket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 数据包的reposity
 *
 * @author Msater Zg
 * @create 2017-11-11 10:53
 */
public interface SysPacketRepository extends JpaRepository<SysPacket, Long> {
}
