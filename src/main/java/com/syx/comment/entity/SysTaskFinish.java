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

    @Column(name = "task_release_id")
    private Long taskReleaseId;

    @Column(name = "task_number")
    private String taskNumber;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_type")
    private int taskType;

    @Column(name = "task_link")
    private String taskLink;

    @Column(name = "task_title")
    private String taskTitle;

    @Column(name = "task_site")
    private String taskSite;

    @Column(name = "task_nick_name")
    private String taskNickName;

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "task_file_url")
    private String taskFileUrl;

    @Column(name = "task_image_url")
    private String taskImageUrl;

    @Column(name = "task_tag")
    private String taskTag;

    @Column(name = "task_explain")
    private String taskExplain;

    @Column(name = "task_status")
    private int taskStatus;

    @Column(name = "task_mark")
    private double taskMark;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "task_check_time")
    private Date taskCheckTime;

    @Column(name = "is_stick")
    private int isStick;

    @Column(name = "is_star")
    private int isStar;

    @Column(name = "check_account")
    private String checkAccount;

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

    public Long getTaskReleaseId() {
        return taskReleaseId;
    }

    public void setTaskReleaseId(Long taskReleaseId) {
        this.taskReleaseId = taskReleaseId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
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

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
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

    public double getTaskMark() {
        return taskMark;
    }

    public void setTaskMark(double taskMark) {
        this.taskMark = taskMark;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Date getTaskCheckTime() {
        return taskCheckTime;
    }

    public void setTaskCheckTime(Date taskCheckTime) {
        this.taskCheckTime = taskCheckTime;
    }

    public String getCheckAccount() {
        return checkAccount;
    }

    public int getIsStick() {
        return isStick;
    }

    public void setIsStick(int isStick) {
        this.isStick = isStick;
    }

    public int getIsStar() {
        return isStar;
    }

    public void setIsStar(int isStar) {
        this.isStar = isStar;
    }

    public void setCheckAccount(String checkAccount) {
        this.checkAccount = checkAccount;
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
