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

    @Column(name = "packet_start_time")
    private Date packetStartTime;

    @Column(name = "packet_end_time")
    private Date packetEndTime;

    @Column(name = "packet_status")
    private Date packetStatus;

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

    public Date getPacketStartTime() {
        return packetStartTime;
    }

    public void setPacketStartTime(Date packetStartTime) {
        this.packetStartTime = packetStartTime;
    }

    public Date getPacketEndTime() {
        return packetEndTime;
    }

    public void setPacketEndTime(Date packetEndTime) {
        this.packetEndTime = packetEndTime;
    }

    public Date getPacketStatus() {
        return packetStatus;
    }

    public void setPacketStatus(Date packetStatus) {
        this.packetStatus = packetStatus;
    }
}
