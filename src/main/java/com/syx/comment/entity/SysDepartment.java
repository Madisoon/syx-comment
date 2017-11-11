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

    @Column(name = "dep_packet_no")
    private String depPacketNo;

    @Column(name = "dep_create_time")
    private Date depCreateTime;

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

    public String getDepPacketNo() {
        return depPacketNo;
    }

    public void setDepPacketNo(String depPacketNo) {
        this.depPacketNo = depPacketNo;
    }

    public Date getDepCreateTime() {
        return depCreateTime;
    }

    public void setDepCreateTime(Date depCreateTime) {
        this.depCreateTime = depCreateTime;
    }
}
