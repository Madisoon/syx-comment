package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 任务配置的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:37
 */
@Entity
@Table(name = "sys_task_config")
public class SysTaskConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_config_name")
    private String taskConfigName;

    @Column(name = "task_mark")
    private int taskMark;

    @Column(name = "task_explain")
    private String taskExplain;

    @Column(name = "task_color")
    private String taskColor;

    @Column(name = "is_yuqing")
    private int isYuqing;

    @Column(name = "packet_no")
    private Long packetNo;

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

    public String getTaskConfigName() {
        return taskConfigName;
    }

    public void setTaskConfigName(String taskConfigName) {
        this.taskConfigName = taskConfigName;
    }

    public int getTaskMark() {
        return taskMark;
    }

    public void setTaskMark(int taskMark) {
        this.taskMark = taskMark;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    public String getTaskColor() {
        return taskColor;
    }

    public void setTaskColor(String taskColor) {
        this.taskColor = taskColor;
    }

    public int getIsYuqing() {
        return isYuqing;
    }

    public void setIsYuqing(int isYuqing) {
        this.isYuqing = isYuqing;
    }

    public Long getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(Long packetNo) {
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
