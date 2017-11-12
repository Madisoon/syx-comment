package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 任务发布的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:38
 */
@Entity
@Table(name = "sys_task_release")
public class SysTaskRelease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "task_create_time")
    private Date taskCreateTime;

    @Column(name = "task_creater")
    private String taskCreater;

    @Column(name = "task_packet_no")
    private String taskPacketNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public String getTaskCreater() {
        return taskCreater;
    }

    public void setTaskCreater(String taskCreater) {
        this.taskCreater = taskCreater;
    }

    public String getTaskPacketNo() {
        return taskPacketNo;
    }

    public void setTaskPacketNo(String taskPacketNo) {
        this.taskPacketNo = taskPacketNo;
    }
}
