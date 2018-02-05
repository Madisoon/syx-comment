package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 数据包的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:14
 */

@Entity
@Table(name = "sys_packet")
public class SysPacket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "packet_no")
    private String packetNo;

    @Column(name = "packet_name")
    private String packetName;

    @Column(name = "packet_end_time")
    private Date packetEndTime;

    @Column(name = "is_using")
    private int isUsing;

    @Column(name = "packet_dep_limit")
    private int packetDepLimit;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public Date getPacketEndTime() {
        return packetEndTime;
    }

    public void setPacketEndTime(Date packetEndTime) {
        this.packetEndTime = packetEndTime;
    }

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

    public int getPacketDepLimit() {
        return packetDepLimit;
    }

    public void setPacketDepLimit(int packetDepLimit) {
        this.packetDepLimit = packetDepLimit;
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
