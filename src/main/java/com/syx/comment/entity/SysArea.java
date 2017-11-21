package com.syx.comment.entity;

import javax.persistence.*;

/**
 * 描述:
 * 地区的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-19 12:45
 */
@Entity
@Table(name = "sys_area")
public class SysArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "area_pid")
    private String areaPid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaPid() {
        return areaPid;
    }

    public void setAreaPid(String areaPid) {
        this.areaPid = areaPid;
    }
}
