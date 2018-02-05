package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 地区系统关联表
 *
 * @author Msater Zg
 * @create 2017-11-21 17:00
 */

@Entity
@Table(name = "sys_area_packet")
public class SysAreaPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_id")
    private Long areaId;

    @Column(name = "packet_id")
    private Long packetId;

    @Column(name = "gmtCreate")
    private Date gmtCreate;

    @Column(name = "gmtModified")
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getPacketId() {
        return packetId;
    }

    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
