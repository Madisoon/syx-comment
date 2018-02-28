package com.syx.comment.repository;

import com.syx.comment.entity.SysAreaPacket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述:
 * 地区数据包的repository
 *
 * @author Msater Zg
 * @create 2017-11-21 17:02
 */
public interface SysAreaPacketRepository extends JpaRepository<SysAreaPacket, Long> {
    /**
     * 判断这个系统是否已经存在了
     *
     * @param packetId
     * @return SysAreaPacket
     */
    SysAreaPacket findSysAreaByPacketId(Long packetId);

}
