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

    @Column(name = "task_status")
    private int taskStatus;

    @Column(name = "task_packet_no")
    private Long taskPacketNo;

    @Column(name = "task_time")
    private Date taskTime;

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

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getTaskPacketNo() {
        return taskPacketNo;
    }

    public void setTaskPacketNo(Long taskPacketNo) {
        this.taskPacketNo = taskPacketNo;
    }

    public Date getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Date taskTime) {
        this.taskTime = taskTime;
    }
}
