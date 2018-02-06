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

    @Column(name = "task_number")
    private String taskNumber;

    @Column(name = "task_config_id")
    private Long taskConfigId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_tag")
    private String taskTag;

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "is_posted")
    private int isPosted;

    @Column(name = "packet_no")
    private String packetNo;

    @Column(name = "task_end_time")
    private String taskEndTime;

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

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Long getTaskConfigId() {
        return taskConfigId;
    }

    public void setTaskConfigId(Long taskConfigId) {
        this.taskConfigId = taskConfigId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPacketNo() {
        return packetNo;
    }

    public void setPacketNo(String packetNo) {
        this.packetNo = packetNo;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
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
