package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 任务完成的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:37
 */
@Entity
@Table(name = "sys_task_finish")
public class SysTaskFinish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_type")
    private int taskType;

    @Column(name = "task_link")
    private String taskLink;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_title")
    private String taskSite;

    @Column(name = "task_nick_name")
    private String taskNickName;

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "task_file_url")
    private String taskFileUrl;

    @Column(name = "task_image_url")
    private String taskImageUrl;

    @Column(name = "task_explain")
    private String taskExplain;

    @Column(name = "task_status")
    private int taskStaus;

    @Column(name = "task_mark")
    private int taskMark;

    @Column(name = "task_feedback")
    private String taskFeedback;

    @Column(name = "task_create_time")
    private Date taskCreateTime;

    @Column(name = "task_creater")
    private String taskCreater;

    @Column(name = "task_packet_no")
    private Long taskPacketNo;

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

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getTaskLink() {
        return taskLink;
    }

    public void setTaskLink(String taskLink) {
        this.taskLink = taskLink;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskSite() {
        return taskSite;
    }

    public void setTaskSite(String taskSite) {
        this.taskSite = taskSite;
    }

    public String getTaskNickName() {
        return taskNickName;
    }

    public void setTaskNickName(String taskNickName) {
        this.taskNickName = taskNickName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getTaskFileUrl() {
        return taskFileUrl;
    }

    public void setTaskFileUrl(String taskFileUrl) {
        this.taskFileUrl = taskFileUrl;
    }

    public String getTaskImageUrl() {
        return taskImageUrl;
    }

    public void setTaskImageUrl(String taskImageUrl) {
        this.taskImageUrl = taskImageUrl;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    public int getTaskStaus() {
        return taskStaus;
    }

    public void setTaskStaus(int taskStaus) {
        this.taskStaus = taskStaus;
    }

    public int getTaskMark() {
        return taskMark;
    }

    public void setTaskMark(int taskMark) {
        this.taskMark = taskMark;
    }

    public String getTaskFeedback() {
        return taskFeedback;
    }

    public void setTaskFeedback(String taskFeedback) {
        this.taskFeedback = taskFeedback;
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

    public Long getTaskPacketNo() {
        return taskPacketNo;
    }

    public void setTaskPacketNo(Long taskPacketNo) {
        this.taskPacketNo = taskPacketNo;
    }
}
