package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 部门的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:26
 */
@Entity
@Table(name = "sys_department")
public class SysDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dep_no")
    private String depNo;

    @Column(name = "dep_name")
    private String depName;

    @Column(name = "packet_no")
    private String packetNo;

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

    public String getDepNo() {
        return depNo;
    }

    public void setDepNo(String depNo) {
        this.depNo = depNo;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
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
